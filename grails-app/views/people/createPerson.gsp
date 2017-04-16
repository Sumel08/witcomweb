<!DOCTYPE html>
<html>
    <head>
        <title>Creating a person</title>
        <meta name="layout" content="witcom" />

        <link rel="stylesheet" type="text/css" href="/assets/styles.css"/>

        <link rel="stylesheet" type="text/css" href="/assets/styles.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/bootstrap-formhelpers.min.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/bootstrap-select.min.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/bootstrap-tags.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/forms.css"/>
        <link rel="stylesheet" type="text/css" href="/assets/bootstrap-select.css"/>

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
                                <g:form name="person" action="savePerson" enctype="multipart/form-data">

                                <fieldset>
                                <legend>Person</legend>

                                  <div class="form-group">
                                    <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Name</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="text" class="form-control" id="name" name="name" placeholder="Name" >
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Surname</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname" >
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Email</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="email" class="form-control" id="email" name="email" placeholder="Email" >
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label for="eventCode" class="col-sm-2 control-label" style="margin-top: 10px;">Phone</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone" >
                                    </div>
                                  </div>

                                  <div class="form-group">
                                    <label class="col-sm-2 control-label" style="margin-top: 10px;">Resume</label>
                                    <div class="col-sm-10" style="margin-top: 10px;">
                                      <textarea class="form-control" id="resmue" name="resume" placeholder="Resumen" rows="3" ></textarea>
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
                                  </fieldset>

                                  <fieldset>
                                    <legend>Place</legend>

                                    <div class="form-group">
                                      <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Place</label>
                                      <div class="col-sm-10" style="margin-top: 10px;">
                                        <select name="place" class="selectpicker" data-live-search="true">
                                          <g:each var="place" in="${places}">
                                            <option value="${place.id}">${place.placeName}</option>
                                          </g:each>
                                        </select>
                                      </div>
                                    </div>

                                  </fieldset>
                                    

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
        <script src="/assets/bootstrap-select.js" type="text/javascript"></script>

        <script type="text/javascript">
            $(document).ready ( function(){
                //setDate();
            });

            
        </script>

    </body>
</html>