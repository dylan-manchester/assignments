package com.hcl.flag.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MetricService {
    Map<String,Integer> hits = new HashMap<String, Integer>();

    public void collectMetric(String data) {
        hits.compute(data, (k, v) -> (v == null) ? 1 : v+1);
    }

    public String getMetrics() {
        List<Map.Entry<String, Integer> > list = new LinkedList<>(hits.entrySet());
        Collections.sort(list, Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)));
        String result = "Hits:<br/>\n";
        for (Map.Entry<String, Integer> entry : list) {
            result+=(entry.getKey()+": "+entry.getValue()+"<br/>\n");
        }
        return result;
    }
}
