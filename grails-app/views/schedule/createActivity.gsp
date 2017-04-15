<!DOCTYPE html>
<html>
    <head>
        <title>New activity</title>
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
                                <g:form name="activity" action="saveActivity" enctype="multipart/form-data">

                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Activity Type</label>
                                          <div class="col-sm-10" style="margin-top: 10px;">
                                            <select name="activityType">
                                              <g:each var="activityType" in="${activitiesType}">
                                                <option value="${activityType.id}">${activityType.name}</option>
                                              </g:each>
                                            </select>
                                          </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                        <label for="beginDate" class="col-sm-2 control-label" style="margin-top: 10px;">Begin Date</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="datetime-local" class="form-control" id="beginDate" name="beginDate" placeholder="Begin Date" >
                                        </div>
                                      </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                        <label for="endDate" class="col-sm-2 control-label" style="margin-top: 10px;">End Date</label>
                                        <div class="col-sm-10" style="margin-top: 10px;">
                                          <input type="datetime-local" class="form-control" id="endDate" name="endDate" placeholder="End Date" >
                                        </div>
                                      </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="title" class="col-sm-2 control-label" style="margin-top: 10px;">Title</label>
                                          <div class="col-sm-10" style="margin-top: 10px;">
                                            <input type="text" class="form-control" id="title" name="title" placeholder="Title" >
                                          </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="subtitle" class="col-sm-2 control-label" style="margin-top: 10px;">Subtitle</label>
                                          <div class="col-sm-10" style="margin-top: 10px;">
                                            <input type="text" class="form-control" id="subtitle" name="subtitle" placeholder="Subtitle" >
                                          </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label class="col-sm-2 control-label" style="margin-top: 10px;">Description</label>
                                          <div class="col-sm-10" style="margin-top: 10px;">
                                            <textarea class="form-control" id="description" name="description" placeholder="Activity Description" rows="3" ></textarea>
                                          </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="subtitle" class="col-sm-2 control-label" style="margin-top: 10px;">Price</label>
                                          <div class="col-sm-10" style="margin-top: 10px;">
                                            <input type="number" min="1" step="any" class="form-control" id="price" name="price" placeholder="Price" >
                                          </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label class="col-sm-2 control-label" style="margin-top: 10px;">Notes</label>
                                          <div class="col-sm-10" style="margin-top: 10px;">
                                            <textarea class="form-control" id="notes" name="notes" placeholder="Notes" rows="3" ></textarea>
                                          </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Place</label>
                                          <div class="col-sm-10" style="margin-top: 10px;">
                                            <select name="place">
                                              <g:each var="place" in="${places}">
                                                <option value="${place.id}">${place.placeName}</option>
                                              </g:each>
                                            </select>
                                          </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="eventName" class="col-sm-2 control-label" style="margin-top: 10px;">Speaker (Speakers)</label>
                                          <div class="col-sm-10" style="margin-top: 10px;">
                                            <select name="speakers" class="selectpicker" multiple>
                                                <g:each var="person" in="${people}">
                                                    <option value="${person.id}">${person.name}</option>      
                                                </g:each>
                                            </select>
                                          </div>
                                        </div>
                                    </div>


                                    <g:hiddenField name="idActivity" id="idActivity" />

                                  <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10" style="margin-top: 10px;">
                                      <button type="submit" class="btn btn-primary">Update</button>
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