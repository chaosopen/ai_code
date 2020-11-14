package com.yeyi.ai.deploy.common.scheduling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeyi.ai.deploy.common.pojo.DeployHostHeartBeatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.List;

@Configuration
public class HealthScheduled {

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0/2 * * * * ?")
    public void test() throws JsonProcessingException {
        DeployHostHeartBeatVo deployHostHeartBeatVo = DeployHostHeartBeatVo.builder().build();
        MemoryMXBean mxb = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = mxb.getHeapMemoryUsage();

        deployHostHeartBeatVo.setMemory(memoryUsage.getUsed());
        deployHostHeartBeatVo.setMaxMemory(memoryUsage.getMax());

        //方法区
//        MemoryUsage nonHeapMemoryUsage = mxb.getNonHeapMemoryUsage();
//
//        deployHostHeartBeatVo.setCodeMemory(nonHeapMemoryUsage.getUsed());
//        deployHostHeartBeatVo.setCodeMaxMemory(nonHeapMemoryUsage.getMax());

        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean pool : pools) {
            MemoryUsage poolUsage = pool.getUsage();
            switch (pool.getName()) {
                case "Code Cache":
                    deployHostHeartBeatVo.setCodeMemory(poolUsage.getUsed());
                    deployHostHeartBeatVo.setCodeMaxMemory(poolUsage.getMax());
                    break;
                case "PS Eden Space":
                    deployHostHeartBeatVo.setEdenMemory(poolUsage.getUsed());
                    deployHostHeartBeatVo.setEdenMaxMemory(poolUsage.getMax());
                    break;
                case "PS Survivor Space":
                    deployHostHeartBeatVo.setSurvivorMemory(poolUsage.getUsed());
                    deployHostHeartBeatVo.setSurvivorMaxMemory(poolUsage.getMax());
                    break;
                case "PS Old Gen":
                    deployHostHeartBeatVo.setOldMemory(poolUsage.getUsed());
                    deployHostHeartBeatVo.setOldMaxMemory(poolUsage.getMax());
                    break;
                case "Metaspace":
                    deployHostHeartBeatVo.setMetaspaceMemory(poolUsage.getUsed());
                    deployHostHeartBeatVo.setMetaspaceMaxMemory(poolUsage.getMax());
                    break;
                default:
                    break;
            }
        }
        deployHostHeartBeatVo.setDeployHostId(12L);
        deployHostHeartBeatVo.setReceiveTime(new Date());

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(deployHostHeartBeatVo);

        HttpEntity<String> requestEntity = new HttpEntity<String>(value, headers);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:8899/receiveServiceHeartBeat", requestEntity, String.class);
        String body = stringResponseEntity.getBody();
        System.out.println(body);
    }
}
