<!DOCTYPE html>
<html>
    <head>
        <title>${evento.name}</title>
        <meta name="layout" content="witcom" />
    </head>
    <body>

        <div class="row">
            <div class="col-md-12">
                <div class="content-box-large">
                    <div class="panel-heading" style="text-align: center;">
                        <div class="panel-title col-md-12"  >
                            <h1>${evento.name}</h1>
                        </div>
                        <div class="col-md-6">
                            <img src="${evento.eventImage.url}" style="width: 100%;">
                        </div>
                        <div class="col-md-6">
                            <div class="col-md-12">
                                <label>Start Date:</label>
                                <g:formatDate format="MMMM dd yyyy hh:mm:ss a" date="${evento.startDate}"/>
                                <br/>
                                <label>End Date:</label>
                                <g:formatDate format="MMMM dd yyyy hh:mm:ss a" date="${evento.endDate}"/>
                                
                            </div>
                            <div class="row">
                                <h1>Description:</h1>
                                <p>${evento.description}</p>
                            </div>
                        </div>
                        
                    </div>
                    <div class="panel-body" style="text-align: center;">
                        <g:if test="${evento.place}">
                            <div class="panel-title col-md-12"  >
                                <h1>${evento.place.placeName}</h1>
                            </div>
                            <div class="col-md-6">
                                <img src="${evento.place.image.url}" style="width: 100%;">
                            </div>

                            <div class="col-md-6" style="text-align: left;">
                                <label>Place:</label>
                                ${evento.place.placeName}<br/>
                                <label>Description:</label>
                                ${evento.place.description}<br/>
                                <label>Email:</label>
                                ${evento.place.email}<br/>
                                <label>Telephone:</label>
                                ${evento.place.telephone}<br/>
                                <label>Website:</label>
                                ${evento.place.website}<br/>
                                <label>Indication:</label>
                                ${evento.place.indication}<br/>
                                <label>Additional Info:</label>
                                ${evento.place.additionalInfo}<br/>

                            </div>
                        </g:if>

                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="content-box-large">
                    <div class="panel-heading">
                        <div class="panel-title" style="text-align: center;" >
                            <h1>Chairs</h1>
                        </div>
                        
                    </div>
                    <div class="panel-body">
                        <g:each var="chair" in="${chairs}">
                            <div class="col-md-12">
                                <div class="col-md-offset-1 col-md-3">
                                    <img src="${chair.people.photo.url}" style="width: 100px; height: 100px">
                                </div>
                                <div class="col-md-offset-2 col-md-4" style="text-align: left;">
                                    ${chair.people.name} ${chair.people.surname}<br/>
                                    Email: ${chair.people.email}<br/>
                                    Telephone: ${chair.people.phone}<br/>
                                    Provenance: ${chair.people.provenance.placeName}
                                </div>
                            </div>
                        </g:each>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>