import com.eventstore.dbclient.*
import java.util.UUID

case object EsConnectorV2 {

  val connectionString = "esdb://localhost:2113?tls=false"
  val settings: EventStoreDBClientSettings = EventStoreDBConnectionString.parse(connectionString)
  val client: EventStoreDBClient = EventStoreDBClient.create(settings)

  def sendEvent(): Unit = {

    val eventData: EventData  = EventData
      .builderAsJson("additional-event", TestData("1","some-value"))
      .eventId(UUID.randomUUID())
      .build()

    val options: AppendToStreamOptions = AppendToStreamOptions.get()
      .expectedRevision(ExpectedRevision.NO_STREAM)

    client.appendToStream("additional-stream", options, eventData)
      .get()

  }

}



