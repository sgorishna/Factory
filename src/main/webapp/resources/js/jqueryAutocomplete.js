function getContextPath() {

    var t = window.location.pathname;
    t = t.substr(0, t.lastIndexOf("/"));
    return t; //window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

$(document).ready(function() {
    $("input#componentName").autocomplete({

        change: function(event,ui)
        {
            if (ui.item==null)
            {
                $("#componentName").val('');
                $("#componentName").focus();
            }
        },

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

                    response(data);
                },
                //if the request fails,A error function to be called.
                error: function(data,type){
                    console.log( type);
                }
            });
        },
        select: function(event,ui){
            event.preventDefault();
            var selectedArr = ui.item.value.split("-");
            $("#componentName").val(selectedArr[0].trim());
            $("#code").val(selectedArr[1].trim());

            return false;
        }
    });

});

$(document).ready(function() {
    $("input#compoundName").autocomplete({

        change: function(event,ui)
        {
            if (ui.item==null)
            {
                $("#compoundName").val('');
                $("#compoundName").focus();
            }
        },

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

                    response(data);
                },

                //if the request fails,A error function to be called.
                error: function(data,type){
                    console.log( type);
                }
            });
        },
        select: function(event,ui){
            event.preventDefault();
            var selectedArr = ui.item.value.split("-");
            $("#compoundName").val(selectedArr[0].trim());
            $("#code").val(selectedArr[1].trim());

            return false;
        }

    });

});



