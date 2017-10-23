function confirmRestoring() {
    var req = new XMLHttpRequest();
    var r = confirm("Are you sure you want to restore previous data?");
    if (r == true) {
    req.open('POST', '/materials', true);
      req.onreadystatechange = function () {
       if (req.readyState === XMLHttpRequest.DONE && req.status === 500){
              alert(req.responseText)
          }
          if(req.readyState == XMLHttpRequest.DONE && req.status == 200) {
          location.href = '/?restore_data';
          }
        };
        }
    else{
    }
    req.send(null);
    }