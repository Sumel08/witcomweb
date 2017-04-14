<!DOCTYPE html>
<html>
    <head>
        <title>Activity Type</title>
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
                        <a href="#tab_1_1" data-toggle="tab">General Info </a>
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
                                <div class="panel-title">Basic Table</div>
                                
                                
                            </div>
                            <div class="panel-body">
                                <table class="table">
                                  <thead>
                                    <tr>
                                      <th>Name</th>
                                      <th>Description</th>
                                      <th>Actions</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <g:each var="activity" in="${activities}">
                                        <tr>
                                          <td>${activity.name}</td>
                                          <td>${activity.description}</td>
                                          
                                          <td>
                                          <a href="/activityType/editActivity/${activity.id}" class="btn btn-warning"> Edit </a>
                                           <button type="button" class="btn btn-danger" >Delete</button></td>
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
        <script src="/assets/sweetalert.min.js"></script>

        <script type="text/javascript">
            $(document).ready ( function(){
                
            });

            
        </script>

    </body>
</html>