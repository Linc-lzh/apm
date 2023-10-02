package com.bit.monitor.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryFilterBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

public class EsServiceImpl {
	@Autowired
	private ElasticsearchTemplate esTemplate;

	Logger logger = Logger.getLogger("EsServiceImpl");
	private volatile long count = 0;
	private volatile long fails = 0;
	private volatile long succeeds = 0;

	public void putData(String nodeJson) {
		try {
			IndexQuery query = new IndexQueryBuilder().withIndexName("bit_test")
					.withType("monitorLog")
					.withSource(nodeJson).build();
			esTemplate.index(query);
			succeeds++;
		} catch (Exception e) {
			e.printStackTrace();
			fails++;
		} finally {
			count++;
		}
	}

	public List<MonitorLog> queryLog(Long begin, Long end, String serviceName) {
		AndFilterBuilder filterBuilder = FilterBuilders.andFilter();
		// 创建时间过滤
		if(begin != null||end != null){
			RangeFilterBuilder timeFilte = FilterBuilders.rangeFilter("createTime");
			if (begin != null) {
				timeFilte.gte(begin);
			}
			if (end != null) {
				timeFilte.lte(end);
			}
			filterBuilder.add(timeFilte);
		}
		// 类别过滤
		QueryBuilder queryBuilder = null;
		if (serviceName != null) {
			queryBuilder = QueryBuilders.queryStringQuery(serviceName);
		}

		SearchQuery query = new NativeSearchQuery(queryBuilder, filterBuilder);
		query.addIndices("dn_test");
		query.addTypes("monitorLog");
		query.setPageable(new PageRequest(0, 1000));
		FacetedPage<MonitorLog> facetedPage = esTemplate.queryForPage(query, MonitorLog.class);
		return facetedPage.getContent();
	}

	public List<StatisticsVo> getStatistics(Long begin, Long end, String serviceName) {
		List<MonitorLog> list = queryLog(begin, end, serviceName);
		Map<String, StatisticsVo> map = new HashMap<>();
		for (MonitorLog log : list) {
			if (!map.containsKey(log.getClassName() + log.getMethodName())) {
				map.put(log.getClassName() + log.getMethodName(), new StatisticsVo());

			}
			StatisticsVo statistic = map.get(log.getClassName() + log.getMethodName());
			statistic.setServiceMethod(log.getMethodName());
			statistic.setServiceName(log.getClassName());
			statistic.count++;

			if (log.getErrorType() != null&&!log.getErrorType().equals("null")) {
				statistic.faills++;
			}
			// 总时间
			statistic.totalTime += log.getEnd() - log.getBegin();

			// 计算最快时间
			if (statistic.fastTime == 0 || statistic.fastTime > (log.getEnd() - log.getBegin())) {
				statistic.fastTime = log.getEnd() - log.getBegin();
			}

			// 统计最慢时间
			if (statistic.slowTime == 0 || statistic.slowTime < (log.getEnd() - log.getBegin())) {
				statistic.slowTime = log.getEnd() - log.getBegin();
			}
			statistic.avgTime = statistic.totalTime / statistic.count;
		}
		List<StatisticsVo> result = new ArrayList<>();
		result.addAll(map.values());
		return result;
	}

	public void getData() {

	}

	public long getCount() {
		return count;
	}

	public long getFails() {
		return fails;
	}

	public long getSucceeds() {
		return succeeds;
	}

}
