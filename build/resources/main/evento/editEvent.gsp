<!DOCTYPE html>
<html>
    <head>
        <title>${evento.name}</title>
        <meta name="layout" content="witcom" />

        <link rel="stylesheet" type="text/css" href="/assets/styles.css"/>

        <link rel="stylesheet" type="text/css" href="/assets/styles.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/bootstrap-formhelpers.min.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/bootstrap-select.min.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/bootstrap-tags.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/forms.css"/>

        <link rel="stylesheet" type="text/css" href="/assets/sweetalert.css">

        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

    </head>
    <body>

        <div class="portlet light">
            <div class="portlet-title tabbable-line">
                <div class="caption caption-md">
                    <i class="icon-globe theme-font hide"></i>
                    <span class="caption-subject font-blue-madison bold uppercase">Información</span>
                </div>
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab_1_1" data-toggle="tab">General Info</a>
                    </li>
                    <li>
                        <a href="#tab_1_2" data-toggle="tab">Chairs</a>
                    </li>
                    <li>
                        <a href="#tab_1_3" data-toggle="tab">Place</a>
                    </li>

                </ul>
            </div>
            <div class="portlet-body">
                <div class="tab-content">
                    <!-- PERSONAL INFO TAB -->
                    <div class="tab-pane active" id="tab_1_1">
                        <!-- BEGIN FORM -->
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title">Horizontal Form</div>
                              
                                <div class="panel-options">
                                  <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                  <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <g:form name="eventInfo" action="updateEvent" enctype="multipart/form-data">

                                  <div class="form-group">
                                    <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Event Name</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="text" class="form-control" id="eventName" name="eventName" placeholder="Event Name" value="${evento.name}">
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Event Code</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="text" class="form-control" id="eventCode" name="eventCode" placeholder="Event Code" value="${evento.code}">
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label class="col-sm-2 control-label" style="margin-top: 10px;">Description</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <textarea class="form-control" id="description" name="description" placeholder="Description" rows="3" >${evento.description}</textarea>
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="startDate" class="col-sm-2 control-label" style="margin-top: 10px;">Start Date</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="datetime-local" class="form-control" id="startDate" name="startDate" placeholder="Start Date" >
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="startDate" class="col-sm-2 control-label" style="margin-top: 10px;">End Date</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="datetime-local" class="form-control" id="endDate" name="endDate" placeholder="End Date" >
                                    </div>
                                  </div>
                                    
                                    <div class="form-group">
                                        <label class="col-md-2 control-label" style="margin-top: 10px;">File input</label>
                                        <div class="col-md-10" style="margin-top: 10px;">
                                            <input type="file" class="btn btn-default" id="eventPhoto" name="eventPhoto" accept="image/*">
                                            
                                        </div>
                                    </div>
                                  
                                    <g:hiddenField name="flag" id="flag" value="0"/>
                                  
                                  <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10" style="margin-top: 10px;">
                                      <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                  </div>
                                </g:form>
                            </div>
                        </div>
                        <!-- END FORM -->
                    </div>

                    <div class="tab-pane " id="tab_1_2">
                        <!-- BEGIN FORM -->
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title">Basic Table</div>
                                
                                
                            </div>
                            <div class="panel-body">
                                <table class="table">
                                  <thead>
                                    <tr>
                                      <th>Name</th>
                                      <th>Surname</th>
                                      <th>Email</th>
                                      <th>Phone</th>
                                      <th>Provenance</th>
                                      <th>Actions</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <g:each var="chair" in="${chairs}">
                                        <tr>
                                          <td>${chair.people.name}</td>
                                          <td>${chair.people.surname}</td>
                                          <td>${chair.people.email}</td>
                                          <td>${chair.people.phone}</td>
                                          <td>${chair.people.provenance.placeName}</td>
                                          <td>
                                          <a href="/people/editPerson/${chair.people.id}" class="btn btn-warning"> Edit </a>
                                           <button type="button" class="btn btn-danger" onclick="deleteChair('${chair.id}')">Delete</button></td>
                                        </tr>
                                    </g:each>
                                  </tbody>
                                </table>
                                <div class="row" style="text-align: right;">
                                <a href="/chairs/createChair/${evento.id}" class="btn btn-success">New Chair</a>
                                </div>
                            </div>
                        </div>
                        <!-- END FORM -->
                    </div>

                    <div class="tab-pane active" id="tab_1_3">
                        <!-- BEGIN FORM -->
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title">Event Place</div>
                              
                                <div class="panel-options">
                                  <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                  <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <g:form name="eventPlace" action="updateEvent" enctype="multipart/form-data">

                                      <div class="form-group">
                                        <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Place Name</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="text" class="form-control" id="plceName" name="placeName" placeholder="Place Name" value="${evento.place?evento.place.placeName:""}">
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label class="col-sm-2 control-label" style="margin-top: 10px;">Place Description</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <textarea class="form-control" id="placeDescription" name="placeDescription" placeholder="Place Description" rows="3" >${evento.place?evento.place.description:""}</textarea>
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Place Phone</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="text" class="form-control" id="placePhone" name="placePhone" placeholder="Place Phone" value="${evento.place?evento.place.telephone:""}">
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Place Email</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="email" class="form-control" id="placeEmail" name="placeEmail" placeholder="Place Email" value="${evento.place?evento.place.email:""}">
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Place Website</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="url" class="form-control" id="placeWebsite" name="placeWebsite" placeholder="Place Website" value="${evento.place?evento.place.website:""}">
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label class="col-sm-2 control-label" style="margin-top: 10px;">Place Indications</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <textarea class="form-control" id="placeIndications" name="placeIndications" placeholder="Place Indications" rows="3" >${evento.place?evento.place.indication:""}</textarea>
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label class="col-sm-2 control-label" style="margin-top: 10px;">Place Additional</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <textarea class="form-control" id="placeAdditional" name="placeAdditional" placeholder="Place Additional" rows="3" >${evento.place?evento.place.additionalInfo:""}</textarea>
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Latitude</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="text" class="form-control" id="latitude" name="latitude" placeholder="Latitude" value="${evento.place?evento.place.latitude:""}">
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Longitude</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="text" class="form-control" id="longitude" name="longitude" placeholder="Longitude" value="${evento.place?evento.place.longitude:""}">
                                        </div>
                                      </div>

                                      <div class="form-group">
                                        <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Altitude</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="text" class="form-control" id="altitude" name="altitude" placeholder="Altitude" value="${evento.place?evento.place.altitude:""}">
                                        </div>
                                      </div>

                                      <div class="form-group">
                                          <label class="col-md-2 control-label" style="margin-top: 10px;">File input</label>
                                          <div class="col-md-10" style="margin-top: 10px;">
                                              <input type="file" class="btn btn-default" id="placeImage" name="placeImage" accept="image/*">
                                              
                                          </div>
                                      </div>
                                      
                                    <g:hiddenField name="flag" id="flag" value="1"/>

                                    <div class="form-group">
                                      <div class="col-sm-offset-2 col-sm-10" style="margin-top: 10px;">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                      </div>
                                    </div>
                                  </g:form>
                            </div>
                        </div>
                        <!-- END FORM -->
                    </div>

                </div>

            </div>
        </div>

        <script src="https://code.jquery.com/jquery.js" type="text/javascript"></script>
        <script src="/assets/sweetalert.min.js"></script>

        <script type="text/javascript">
            $(document).ready ( function(){
                setDates();
            });

            function setDates() {
                var startDate = "${evento.startDate}";
                startDate = startDate.split(" ");
                var endDate = "${evento.endDate}";
                endDate = endDate.split(" ");
                if (startDate.length == 2)
                    document.getElementById('startDate').value = startDate[0] + "T" + startDate[1];
                if (endDate.length == 2)
                    document.getElementById('endDate').value = endDate[0] + "T" + endDate[1];
            }

            function deleteChair(id) {
              swal({   
                title: "Are you sure?",   
                text: "You will not be able to recover this chair!",   
                type: "warning",   
                showCancelButton: true,   
                confirmButtonColor: "#DD6B55",   
                confirmButtonText: "Yes, delete it!",   
                cancelButtonText: "No, cancel plx!",   
                closeOnConfirm: false,   
                closeOnCancel: false 
              }, function(isConfirm){   
                if (isConfirm) {     
                  //swal("Deleted!", "Your chair has been deleted.", "success");
                  location.href = "/chairs/deleteChair/" + id;
                } else {     
                  swal("Cancelled", "Your chair is safe :)", "error");   
                } 
              });
            }
        </script>

    </body>
</html>