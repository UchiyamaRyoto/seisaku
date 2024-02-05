
      $(function() {
        function initMap() {
          var pos = { lat: 35.1201551, lng: 136.9120932 };
          var opts = {
            zoom: 15,
            center: new google.maps.LatLng(pos.lat, pos.lng),
          };
          var ele = document.getElementById("map");
          var map = new google.maps.Map(ele, opts);
          var marker = new google.maps.Marker({
            position: pos,
            map: map,
          });

          var directionsService = new google.maps.DirectionsService();
          var directionsRenderer = new google.maps.DirectionsRenderer({
            map: map,
          });

          var origin = new google.maps.LatLng(35.1201551, 136.9120932);
          var destination = new google.maps.LatLng(35.0507424, 136.8444393);

          var request = {
            origin: origin,
            destination: destination,
            travelMode: google.maps.TravelMode.DRIVING,
          };

          directionsService.route(request, function(result, status) {
            if (status == google.maps.DirectionsStatus.OK) {
              directionsRenderer.setDirections(result);
            } else {
              console.log("失敗");
            }
          });
        }
        initMap();
      });
