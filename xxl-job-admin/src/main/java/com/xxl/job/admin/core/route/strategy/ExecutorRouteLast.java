package com.xxl.job.admin.core.route.strategy;

import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.core.route.ExecutorRouter;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;

import java.util.ArrayList;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteLast extends ExecutorRouter {

    public String route(int jobId, ArrayList<String> addressList) {
        return addressList.get(addressList.size()-1);
    }

    @Override
    public ReturnT<String> routeRun(TriggerParam triggerParam, ArrayList<String> addressList, XxlJobLog jobLog) {
        // address
        String address = route(triggerParam.getJobId(), addressList);
        jobLog.setExecutorAddress(address);

        // run executor
        ReturnT<String> runResult = runExecutor(triggerParam, address);
        runResult.setMsg("<br>----------------------<br>" + runResult.getMsg());

        return runResult;
    }
}
