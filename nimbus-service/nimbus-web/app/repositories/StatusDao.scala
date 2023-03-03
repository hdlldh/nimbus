package repositories

import javax.inject.Inject

import scala.concurrent.Future

import com.goku.nimbus.db.StatusTable
import com.goku.nimbus.models.Status
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

class StatusDao @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def insertOrUpdate(status: Status): Future[Int] = db.run(StatusTable().insertOrUpdate(status))

  def findByApp(appName: String): Future[Seq[Status]] =
    db.run(StatusTable().filter(_.appName === appName).result)

  def delete(appName: String, taskName: String): Future[Int] =
    db.run(StatusTable().filter(r => r.appName === appName && r.taskName === taskName).delete)

  def deleteApp(appName: String): Future[Int] =
    db.run(StatusTable().filter(_.appName === appName).delete)

  def deleteTask(taskName: String): Future[Int] =
    db.run(StatusTable().filter(_.taskName === taskName).delete)

}
