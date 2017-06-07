package br.com.programmer.grid;

import com.google.common.collect.Lists;
import org.jppf.client.JPPFClient;
import org.jppf.client.JPPFJob;
import org.jppf.node.protocol.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lucas on 6/4/2017.
 */
@Service
public class Grid <T> implements GridProvider{

    private static final long serialVersionUID = -7917665383559446254L;

    @Autowired
    private ApplicationContext context;

    private Iterable list;
    private int blockSize = 1;

    @Override
    public void run() {
        try {
            JPPFClient jppfClient = new JPPFClient();
            executeBlockingJob(jppfClient);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setList(Iterable list) {
        this.list = list;
    }

    @Override
    public void setBlockSize(int blockSize){
        this.blockSize = blockSize;
    }

    private void executeBlockingJob(final JPPFClient jppfClient) throws Exception {
        // Create a job
        JPPFJob job = criarJob("Database Migration");
        // set the job in blocking mode.
        job.setBlocking(true);
        // Submit the job and wait until the results are returned.
        // The results are returned as a list of Task<?> instances,
        // in the same order as the one in which the tasks where initially added to the job.
        List<Task<?>> results = jppfClient.submitJob(job);
        // process the results
        processarResultados(job.getName(), results);
        System.out.println("Todos os jobs foram enviados para processamento");
    }

    private JPPFJob criarJob(final String jobName) throws Exception {
        JPPFJob job = new JPPFJob();
        job.setName(jobName);

        int currentBlock = 0, numBlocks = 0;
        ArrayList l = Lists.newArrayList(list);
        numBlocks = l.size() / blockSize;
        for (currentBlock = 0; currentBlock < numBlocks; currentBlock++){
            Task<?> task = job.add(context.getBean(TaskProvider.class));
            task.setId("ID: " + jobName + " - " + new Random().nextInt(100));
            TaskProvider t = ((TaskProvider) task);
            t.setList(new ArrayList<T>(l.subList(currentBlock * blockSize, ((currentBlock + 1) * blockSize) - 1)));
        }
        return job;
    }

    private synchronized void processarResultados(final String jobName, final List<Task<?>> results) {
        // print a results header
        System.out.printf("Resultado para o Job: %s' :\n", jobName);
        // process the results
        for (Task<?> task : results) {
            String taskName = task.getId();
            // if the Task execution resulted in an exception
            if (task.getThrowable() != null) {
                // process the exception here ...
                System.out.println("Uma exceção foi lançada para a task '" + taskName + "'\nErro: " + task.getThrowable().getMessage());
                task.getThrowable().printStackTrace();
            } else {
                // process the result here ...
                System.out.println("A execução da task '"+ taskName + "' foi finalizada com sucesso. Segue abaixo o resultado:\n" + task.getResult());
            }
        }
    }
}
