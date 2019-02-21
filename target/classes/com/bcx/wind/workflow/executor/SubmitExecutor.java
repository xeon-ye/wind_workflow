package com.bcx.wind.workflow.executor;

import com.bcx.wind.workflow.core.Actuator;
import com.bcx.wind.workflow.core.flow.NodeModel;
import com.bcx.wind.workflow.core.pojo.Task;

import java.util.List;

/**
 * 提交执行
 */
public class SubmitExecutor extends BaseExecutor {


    public SubmitExecutor(Actuator actuator) {
        super(actuator);
    }

    @Override
    public void executor() {
        buildWorkflow();
        exec();
    }


    private void exec(){
        List<Task> tasks = workflow().getCurTask();
        for(Task task : tasks){
            NodeModel nodeModel = task.getTaskModel();
            nodeModel.actuator(this.actuator);
            nodeModel.task(task);
            nodeModel.execute();
        }
    }




    private void buildWorkflow(){
        //查询流程实例
        buildOrderInstance();

        this.actuator.getWorkflow().setVariable(variable()).setApproveUsers(variable().getApproveUsers())
                .setSystem(variable().getSystem())
                .setBusinessId(variable().getBusinessId())
                .setUser(variable().getUser());

        //当前任务
        buildCurTask();
    }





}
