/*menu handler*/
$(function(){
  function stripTrailingSlash(str) {
    if(str.substr(-1) == '/') {
      return str.substr(0, str.length - 1);
    }
    return str;
  }

  var url = window.location.pathname;
  var activePage = stripTrailingSlash(url);

  $('.nav li a').each(function(){
    var currentPage = stripTrailingSlash($(this).attr('href'));

    if (activePage == currentPage) {
      $(this).parent().addClass('active');
    }
  });
});
$('#sidebar-toggle-button').click(
function() {
    //Calculate the current width of the column
    if(parseInt($('#sidebar-wrapper').css("width")) > 10) {
        //The column is big make it small
        $('#sidebar-wrapper').css('width', '0')
    }
    else {
        //The column is small make it big
        $('#sidebar-wrapper').css('width', '250px')
    }

})
