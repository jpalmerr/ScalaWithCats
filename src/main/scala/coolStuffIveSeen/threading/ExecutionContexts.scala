package coolStuffIveSeen.threading

import java.util.concurrent.{ExecutorService, Executors, ThreadFactory}
import java.util.concurrent.atomic.AtomicLong
import scala.concurrent.ExecutionContext

object ExecutionContexts {

  private val availableProcessors: Int = sys.runtime.availableProcessors()
  // returns number of available processors on jvm

  /**
   * Parallelism is having multiple processes working at the same time on multiple CPU's
   * Use an int to let us play with how many based on jvm memory being used
   * Remember must always be an even
   */
  lazy val main: ExecutionContext =
    createFixedSizeExecutionContext("http-server", 32)

  lazy val clientsExecutionContext: ExecutionContext =
    createFixedSizeExecutionContext("client", 16)

  /**
   * An {@link Executor} that provides methods to manage termination and
   * methods that can produce a Future for tracking progress of
   * one or more asynchronous tasks.
   */

  private def createFixedSizeExecutionContext(namePrefix: String, parallelismMultiplier: Int): ExecutionContext =
  // Creates an ExecutionContext from the given Executor with the default reporter.
    ExecutionContext.fromExecutor(
      createFixedSizeExecutorService(namePrefix, availableProcessors * parallelismMultiplier)
    )

  /**
   * Creates a thread pool that reuses a fixed number of threads
   * operating off a shared unbounded queue, using the provided
   * ThreadFactory to create new threads when needed.
   */
  private def createFixedSizeExecutorService(namePrefix: String, threads: Int): ExecutorService =
    Executors.newFixedThreadPool(threads, createThreadFactory(namePrefix))

  /**
   * Constructs a new Thread
   */
  private def createThreadFactory(namePrefix: String): ThreadFactory =
    new ThreadFactory {
      val threadGroup: ThreadGroup =
        Option(System.getSecurityManager).map(_.getThreadGroup).getOrElse(Thread.currentThread().getThreadGroup)

      val counter = new AtomicLong(1L)

      override def newThread(r: Runnable): Thread =
        new Thread(threadGroup, r, s"$namePrefix-${counter.getAndIncrement().toString}")
    }
}
