<!DOCTYPE html>
<html>
    <head>
        <title>Editing ${person.name}</title>
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

                </ul>
            </div>
            <div class="portlet-body">
                <div class="tab-content">
                    <!-- PERSONAL INFO TAB -->
                    <div class="tab-pane active" id="tab_1_1">
                        <!-- BEGIN FORM -->
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title">Horizontal Form </div>
                                
                                <div class="panel-options">
                                  <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                  <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <g:form name="eventInfo" action="updatePerson" enctype="multipart/form-data">

                                  <div class="form-group">
                                    <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Name</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${person.name}">
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Surname</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname" value="${person.surname}">
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Email</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${person.email}">
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Phone</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone" value="${person.phone}">
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label class="col-sm-2 control-label" style="margin-top: 10px;">Resume</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <textarea class="form-control" id="resmue" name="resume" placeholder="Resumen" rows="3" >${person.resume}</textarea>
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="startDate" class="col-sm-2 control-label" style="margin-top: 10px;">Date of birth</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder="Date Of Birth" >
                                    </div>
                                  </div>
                                    
                                    <div class="form-group">
                                        <label class="col-md-2 control-label" style="margin-top: 10px;">File input</label>
                                        <div class="col-md-10" style="margin-top: 10px;">
                                            <input type="file" class="btn btn-default" id="profilePhoto" name="profilePhoto" accept="image/*">
                                            
                                        </div>
                                    </div>

                                    <fieldset>
                                      <legend>Place</legend>

                                      <div class="form-group">
                                        <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Place</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <select name="place">
                                            <g:if test="${person.provenance}">
                                              <g:each var="place" in="${places}">
                                                <g:if test="${place.id == person.provenance.id}">
                                                  <option value="${place.id}" selected>${place.placeName}</option>
                                                </g:if>
                                                <g:else>
                                                  <option value="${place.id}">${place.placeName}</option>
                                                </g:else>
                                              </g:each>
                                            </g:if>
                                            <g:else>
                                              <g:each var="place" in="${places}">
                                                <option value="${place.id}">${place.placeName}</option>
                                              </g:each>
                                            </g:else>
                                          </select>
                                        </div>
                                      </div>

                                    </fieldset>
                                  
                                    <g:hiddenField name="idPerson" id="idPerson" value="${person.id}"/>
                                  
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
                        
                        <!-- END FORM -->
                    </div>

                </div>

            </div>
        </div>

        <script src="https://code.jquery.com/jquery.js" type="text/javascript"></script>

        <script type="text/javascript">
            $(document).ready ( function(){
                setDate();
            });

            function setDate() {
                var dob = "${person.birthdate}";
                dob = dob.split(" ");
                
                if (dob.length == 2)
                    document.getElementById('dateOfBirth').value = dob[0]
                
            }
        </script>

    </body>
</html>