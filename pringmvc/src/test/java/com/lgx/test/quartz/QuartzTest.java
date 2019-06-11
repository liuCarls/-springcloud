package com.lgx.test.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {

    public static void main(String[] args) {
        try {
            QuartzTest.test1();
            while (true) {
                Thread.sleep(1000*5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1() throws Exception{
        // 创建调度器，
        //读取quartz配置文件，默认path下的quartz.properties文件
        //根据配置设置调度器
        SchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();

        // define the job and tie it to our com.lgx.test.quartz.HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("myJob", "group1")
                .usingJobData("jobSays", "Hello World!")
                .usingJobData("myFloatValue", 3.141f)
                .build();

        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
        //调度器开始调度任务
        sched.start();
    }
}

/**
 *
 */

/**https://www.cnblogs.com/longqingyang/p/7027897.html
 Quartz API的关键接口是：

 Scheduler - 与调度程序交互的主要API。
    Scheduler的生命期，从SchedulerFactory创建它时开始，
    到Scheduler调用shutdown()方法时结束；Scheduler被创建后，
    可以增加、删除和列举Job和Trigger，以及执行其它与调度相关的操作
 SchedulerListener
    与计划程序相关的事件包括：添加job/触发器，删除job/触发器，
    调度程序中的严重错误，关闭调度程序的通知等。

 Job - 由希望由调度程序执行的组件实现的接口。

 JobDetail /JobDetailImpl类- 用于定义作业的实例。
    用来描述一个job,定义了job所有属性:
     class	必须是job实现类（比如JobImpl），用来绑定一个具体job
     name	job名称。如果未指定，会自动分配一个唯一名称。所有job都必须拥有一个唯一name，如果两个job的name重复，则只有最前面的job能被调度
     group	job所属的组名
     description	job描述
     durability	是否持久化。如果job设置为非持久，当没有活跃的trigger与之关联的时候，job会自动从scheduler中删除。也就是说，非持久job的生命期是由trigger的存在与否决定的
     shouldRecover	是否可恢复。如果job设置为可恢复，一旦job执行时scheduler发生hard shutdown（比如进程崩溃或关机），当scheduler重启后，该job会被重新执行
     jobDataMap	除了上面常规属性外，用户可以把任意kv数据存入jobDataMap，实现job属性的无限制扩展，执行job时可以使用这些属性数据。此属性的类型是JobDataMap，实现了Serializable接口，可做跨平台的序列化传输
 JobBuilder类- 用于定义/构建JobDetail实例，用于定义作业的实例。


 Trigger（即触发器） - 定义执行给定作业的计划的组件。
 有SimpleTriggerImpl类 / SimpleTrigger接口 / Trigger接口，用来保存触发job的策略，比如每隔几秒触发job。
 实际上，quartz有两大触发器：SimpleTrigger和CronTrigger

     属性名	属性类型	说明
     name	所有trigger通用	trigger名称
     group	所有trigger通用	trigger所属的组名
     description	所有trigger通用	trigger描述
     calendarName	所有trigger通用	日历名称，指定使用哪个Calendar类，经常用来从trigger的调度计划中排除某些时间段
     misfireInstruction	所有trigger通用	错过job（未在指定时间执行的job）的处理策略，默认为MISFIRE_INSTRUCTION_SMART_POLICY。详见这篇blog[5]
     priority	所有trigger通用	优先级，默认为5。当多个trigger同时触发job时，线程池可能不够用，此时根据优先级来决定谁先触发
     jobDataMap	所有trigger通用	同job的jobDataMap。假如job和trigger的jobDataMap有同名key，通过getMergedJobDataMap()获取的jobDataMap，将以trigger的为准
     startTime	所有trigger通用	触发开始时间，默认为当前时间。决定什么时间开始触发job
     endTime	所有trigger通用	触发结束时间。决定什么时间停止触发job
     nextFireTime	SimpleTrigger私有	下一次触发job的时间
     previousFireTime	SimpleTrigger私有	上一次触发job的时间
     repeatCount	SimpleTrigger私有	需触发的总次数
     timesTriggered	SimpleTrigger私有	已经触发过的次数
     repeatInterval	SimpleTrigger私有	触发间隔时间


 TriggerListeners接收到与触发器（trigger）相关的事件，
 JobListeners 接收与jobs相关的事件。

 JobBuilder
 TriggerBuilder - 用于定义/构建触发器实例。

 JobStore负责跟踪您提供给调度程序的所有“工作数据”：jobs，triggers，日历等。
    quartz有两大jobStore：
        1. RAMJobStore内存持久化
        2.JDBCJobStore（JobStoreTX 非应用服务器， JobStoreCMT应用服务器）
 */


//public Scheduler getScheduler() throws SchedulerException {
//    // 读取quartz配置文件，未指定则顺序遍历各个path下的quartz.properties文件
//    // 解析出quartz配置内容和环境变量，存入PropertiesParser对象
//    // PropertiesParser组合了Properties（继承Hashtable），定义了一系列对Properties的操作方法，比如getPropertyGroup()批量获取相同前缀的配置。配置内容和环境变量存放在Properties成员变量中
//    if (cfg == null) {
//        initialize();
//    }
//    // 获取调度器池，采用了单例模式
//    // 其实，调度器池的核心变量就是一个hashmap，每个元素key是scheduler名，value是scheduler实例
//    // getInstance()用synchronized防止并发创建
//    SchedulerRepository schedRep = SchedulerRepository.getInstance();
//
//    // 从调度器池中取出当前配置所用的调度器
//    Scheduler sched = schedRep.lookup(getSchedulerName());
//
//        ......
//
//    // 如果调度器池中没有当前配置的调度器，则实例化一个调度器，主要动作包括：
//    // 1）初始化threadPool(线程池)：开发者可以通过org.quartz.threadPool.class配置指定使用哪个线程池类，比如SimpleThreadPool。先class load线程池类，接着动态生成线程池实例bean，然后通过反射，
//　　　　 //　　　使用setXXX()方法将以org.quartz.threadPool开头的配置内容赋值给bean成员变量；
//    // 2）初始化jobStore(任务存储方式)：开发者可以通过org.quartz.jobStore.class配置指定使用哪个任务存储类，比如RAMJobStore。先class load任务存储类，接着动态生成实例bean，然后通过反射，
//　　　　//　 　使用setXXX()方法将以org.quartz.jobStore开头的配置内容赋值给bean成员变量；
//    // 3）初始化dataSource(数据源)：开发者可以通过org.quartz.dataSource配置指定数据源详情，比如哪个数据库、账号、密码等。jobStore要指定为JDBCJobStore，dataSource才会有效；
//    // 4）初始化其他配置：包括SchedulerPlugins、JobListeners、TriggerListeners等；
//    // 5）初始化threadExecutor(线程执行器)：默认为DefaultThreadExecutor；
//    // 6）创建工作线程：根据配置创建N个工作thread，执行start()启动thread，并将N个thread顺序add进threadPool实例的空闲线程列表availWorkers中；
//    // 7）创建调度器线程：创建QuartzSchedulerThread实例，并通过threadExecutor.execute(实例)启动调度器线程；
//    // 8）创建调度器：创建StdScheduler实例，将上面所有配置和引用组合进实例中，并将实例存入调度器池中
//    sched = instantiate();
//
//    return sched;
//}