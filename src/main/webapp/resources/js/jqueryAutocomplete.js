function getContextPath() {

    var t = window.location.pathname;
    t = t.substr(0, t.lastIndexOf("/"));
    return t; //window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

$(document).ready(function() {
    $("input#componentName").autocomplete({

        source: function(request, response) {
            $.ajax({
                // basePath is used for defining contecxt-path of the url.
                url:  getContextPath()+"/componentAutocomplete",
                dataType: "json",
                // data to be sent to the server:
                data: {
                    term : request.term

                },
                /*
                 A Success function to be called if the request succeeds.The function gets
                 passed two arguments-
                 The data returned from the server, a string describing the status:
                 */
                success: function(data,type) {
                    console.log( data);
                    items = data;
                    response(items);
                },
                //if the request fails,A error function to be called.
                error: function(data,type){
                    console.log( type);
                }
            });
        }
    });

});

$(document).ready(function() {
    $("input#compoundName").autocomplete({

        source: function(request, response) {
            $.ajax({
                // basePath is used for defining contecxt-path of the url.
                url:  getContextPath()+"/compoundAutocomplete",
                dataType: "json",
                // data to be sent to the server:
                data: {
                    term : request.term

                },
                /*
                 A Success function to be called if the request succeeds.The function gets
                 passed two arguments-
                 The data returned from the server, a string describing the status:
                 */
                success: function(data,type) {
                    console.log( data);
                    items = data;
                    response(items);
                },
                //if the request fails,A error function to be called.
                error: function(data,type){
                    console.log( type);
                }
            });
        }
    });

});



