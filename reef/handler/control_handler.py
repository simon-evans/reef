import handlers
import restlite
import vazelsmanager

### Manage starting & stopping the server ###

@restlite.resource
def control_handler():
  def GET(request):
    handlers.login(request)
    if vazelsmanager.vazelsRunning():
      return request.response({"control_centre_status": "running"})
    else:
      return request.response({"control_centre_status": "ready"})
  
  def POST(request, entity):
    return GET(request)
  
  return locals()

@restlite.resource
def start_handler():
  def GET(request):
    handlers.login(request)
    return request.response("Got a start GET request")
  
  def POST(request, entity):
    handlers.login(request)
      
    os_call_to_vazels = vazelsmanager.runVazels()
      
    if os_call_to_vazels :
      # Need to upadate client to handle 204 as a successful response
      return request.response("")
    else :
      raise restlite.Status("500 Vazels Control Centre failed to start")
    
  return locals()

@restlite.resource
def stop_handler():
  def GET(request):
    handlers.login(request)
    return request.response("Got a stop GET request")
  
  def POST(request,entity):
    handlers.login(request)
    
    os_call_to_vazels = vazelsmanager.stopVazels()
    
    if os_call_to_vazels is True :
      return request.response("")
    else :
      raise restlite.Status("500 "+str(os_call_to_vazels))
      
  return locals()