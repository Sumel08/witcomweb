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
                                
                                <div class="panel-options">
                                    <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                    <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                </div>
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
                                           <button type="button" class="btn btn-danger">Delete</button></td>
                                        </tr>
                                    </g:each>
                                  </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- END FORM -->
                    </div>

                </div>

            </div>
        </div>

        <script src="https://code.jquery.com/jquery.js" type="text/javascript"></script>

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
        </script>

    </body>
</html>