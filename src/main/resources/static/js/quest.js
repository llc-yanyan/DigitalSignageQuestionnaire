/** push! push! */
$(function() {
  $('#pushpush-btn').click(
    function() {
      var hostUrl = '/mobile/';
      $.ajax({
        url : hostUrl,
        type : 'POST',
        timeout : 10000,
        data : {type: 'push', qNo: 1, text: 'いいね！'},
      }).done(function(data, textStatus, jqXHR) {
        console.log('done', jqXHR.status);
      }).fail(function(jqXHR, textStatus, errorThrown) {
        console.log('fail', jqXHR.status);
      })
  });
});

$(function() {
	  $('#text-btn').click(
	    function() {
	      var hostUrl = '/mobile/';
	      $.ajax({
	        url : hostUrl,
	        type : 'POST',
	        timeout : 10000,
	        data : {type: 'text', qNo: 2, text: $("#send-text").val()},
	      }).done(function(data, textStatus, jqXHR) {
	        console.log('done', jqXHR.status);
	      }).fail(function(jqXHR, textStatus, errorThrown) {
	        console.log('fail', jqXHR.status);
	      })
	  });
	});

$(function() {
    $('#ajax-button').click(
        function() {
            var hostUrl = '/mobile/';
            $.ajax({
                url : hostUrl,
                type : 'POST',
                timeout : 10000,
            }).done(function(data, textStatus, jqXHR) {
                console.log('done', jqXHR.status);
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log('fail', jqXHR.status);
            })
        });
});
