package com.goku.nimbus.models

import java.sql.Timestamp
case class Status(
  appName: String,
  taskName: String,
  taskStatus: String,
  createdAt: Timestamp,
  updatedAt: Timestamp
)
