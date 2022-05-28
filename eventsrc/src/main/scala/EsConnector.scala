import com.eventstore.dbclient.{EventData, EventStoreDBClient, EventStoreDBClientSettings, EventStoreDBConnectionString, ReadStreamOptions, ResolvedEvent}

case object EsConnector {

  val connectionString = "esdb://localhost:2113?tls=false"
  val settings: EventStoreDBClientSettings = EventStoreDBConnectionString.parse(connectionString)
  val client: EventStoreDBClient = EventStoreDBClient.create(settings)

  def sendEvent(): Unit = {

    val eventData: EventData  = EventData
      .builderAsJson("TestEvent", "abc")
      .build()

    client.appendToStream("some-stream", eventData)
      .get()
  }

  def readEvents(): Unit = {

    val readStreamOptions = ReadStreamOptions.get()
      .forwards()
      .fromStart()

    val result = client.readStream("some-stream", 10, readStreamOptions)
      .get()

    import scala.jdk.CollectionConverters._
    val rs = result.getEvents.asScala.toList

    rs.foreach(e => println("----"+new String(e.getEvent.getEventData, "UTF-8")))
  }

}



