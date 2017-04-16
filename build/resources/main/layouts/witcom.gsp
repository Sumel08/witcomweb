<!DOCTYPE html>
<html>
  <head>
    <title><g:layoutTitle default="WitcomPoli"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.min.css"/>
    <!-- styles -->
    <link rel="stylesheet" type="text/css" href="/assets/styles.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <g:layoutHead/>
  </head>
  <body>
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-10">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><img src="/assets/witcomLogo.png"><a href="index.html"> WITCOM <font style="color: #fcac45"> 2017 </font></a></h1>
	              </div>
	           </div>
	           
	           <div class="col-md-2">
	              <div class="navbar navbar-inverse" role="banner">
	                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
	                    <ul class="nav navbar-nav">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
	                        <ul class="dropdown-menu animated fadeInUp">
	                          <li><a href="profile.html">Profile</a></li>
	                          <li><a href="login.html">Logout</a></li>
	                        </ul>
	                      </li>
	                    </ul>
	                  </nav>
	              </div>
	           </div>
	        </div>
	     </div>
	</div>

    <div class="page-content">
    	<div class="row">
		  <div class="col-md-2">
		  	<div class="sidebar content-box" style="display: block;">
                <ul class="nav">
                    <!-- Main menu -->
                    <li class="submenu">
                         <a href="eventInfo">
                            <i class="glyphicon glyphicon-home"></i> Evento
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="/evento/eventInfo">Event Info</a></li>
                            <li><a href="/evento/editEvent">Edit event</a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                         <a href="/activityType/activities">
                            <i class="glyphicon glyphicon-home"></i> Activity Type
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="/activityType/activities">All Activities Type</a></li>
                            <li><a href="/activityType/createActivity">New Activity Type</a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                         <a href="/schedule/schedule">
                            <i class="glyphicon glyphicon-calendar"></i> Schedule
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="/schedule/schedule">Schedule</a></li>
                            <li><a href="/schedule/createActivity">New Activity</a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                         <a href="/people/people">
                            <i class="glyphicon glyphicon-pencil"></i> Speakers
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="/people/people">Speakers</a></li>
                            <li><a href="/people/createPerson">New Speaker</a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                         <a href="/place/places">
                            <i class="glyphicon glyphicon-pencil"></i> Places
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="/place/places">Places</a></li>
                            <li><a href="/place/createPlace">New Place</a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                         <a href="/placeCategory/placeCategories">
                            <i class="glyphicon glyphicon-pencil"></i> Place Category
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="/placeCategory/placeCategories">Places</a></li>
                            <li><a href="/placeCategory/createPlaceCategory">New Place Category</a></li>
                        </ul>
                    </li>
                    <li><a href="forms.html"><i class="glyphicon glyphicon-tasks"></i> Tourism</a></li>
                    <li class="submenu">
                         <a href="#">
                            <i class="glyphicon glyphicon-list"></i> Pages
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="login.html">Login</a></li>
                            <li><a href="signup.html">Signup</a></li>
                        </ul>
                    </li>
                </ul>
             </div>
		  </div>
		  <div class="col-md-10">
		  	<g:layoutBody />
		  </div>
		</div>
    </div>

    <footer>
         <div class="container">
         
            <div class="copy text-center">
               Copyright 2014 <a href='#'>Website</a>
            </div>
            
         </div>
      </footer>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js" type="text/javascript"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/assets/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/assets/custom.js" type="text/javascript"></script>

    <g:pageProperty name="page.mis_js"/>
  </body>
</html>