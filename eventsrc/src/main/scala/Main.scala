@main def hello(): Unit = {
  handleEvents()
  println("Hello world!")
}

def handleEvents(): Unit = {
  EsConnector.sendEvent()
  EsConnector.readEvents()
}