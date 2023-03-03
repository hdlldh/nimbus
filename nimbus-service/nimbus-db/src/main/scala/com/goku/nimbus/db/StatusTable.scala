package com.goku.nimbus.db

import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{PrimaryKey, ProvenShape}
import com.goku.nimbus.models.Status
class StatusTable(tag: Tag) extends Table[Status](tag, _tableName = "status") {

  def appName: Rep[String] = column[String]("app_name", O.SqlType("VARCHAR(32)"))
  def taskName: Rep[String] = column[String]("task_name", O.SqlType("VARCHAR(32)"))
  def taskStatus: Rep[String] = column[String]("task_status", O.SqlType("VARCHAR(32)"))
  def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")
  def updatedAt: Rep[Timestamp] = column[Timestamp]("updated_at")
  def primaryKey: PrimaryKey = primaryKey("status_pkey", (appName, taskName))

  def * : ProvenShape[Status] = (
    appName,
    taskName,
    taskStatus,
    createdAt,
    updatedAt
  ).mapTo[Status]
}

object StatusTable {
  def apply() = TableQuery[StatusTable]
}
