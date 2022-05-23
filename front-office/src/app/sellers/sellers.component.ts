import { Component, OnInit } from '@angular/core';
declare const L : any ;

@Component({
  selector: 'app-sellers',
  templateUrl: './sellers.component.html',
  styleUrls: ['./sellers.component.css']
})
export class SellersComponent implements OnInit {
  ngOnInit() {
    if (!navigator.geolocation) {
      console.log('location is not supported');
    }
    navigator.geolocation.getCurrentPosition((position) => {
      const coords = position.coords;
      const latLong = [coords.latitude, coords.longitude];
      console.log(
        `lat: ${position.coords.latitude}, lon: ${position.coords.longitude}`
      );
     let mymap = L.map('map').setView(latLong, 13);
     L.tileLayer(
      'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1Ijoic3VicmF0MDA3IiwiYSI6ImNrYjNyMjJxYjBibnIyem55d2NhcTdzM2IifQ.-NnMzrAAlykYciP4RP9zYQ',
      {
        attribution:
          'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1,
        accessToken: 'your.mapbox.access.token',
      }
    ).addTo(mymap);
    L.marker([40.733235, 8.5467447]).addTo(mymap);
    L.marker([45.4842277, 9.2012376]).addTo(mymap);
    L.marker([44.7115622, 10.6027584]).addTo(mymap);
    L.marker([44.6517905, 10.8603544]).addTo(mymap);
    L.marker([41.8933203, 12.4829321]).addTo(mymap);
    L.marker([39.6205476, 16.5174167]).addTo(mymap);
    L.marker([39.3019161, 16.253962]).addTo(mymap);
    L.marker([38.690483, 16.1096073]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);
    L.marker([37.775408, -122.413682]).addTo(mymap);


  });
  this.watchPosition();}
  watchPosition() {
    let desLat = 0;
    let desLon = 0;
    let id = navigator.geolocation.watchPosition(
      (position) => {
        console.log(
          `lat: ${position.coords.latitude}, lon: ${position.coords.longitude}`
        );
        if (position.coords.latitude === desLat) {
          navigator.geolocation.clearWatch(id);
        }
      },
      (err) => {
        console.log(err);
      },
      {
        enableHighAccuracy: true,
        timeout: 5000,
        maximumAge: 0,
      }
    );
  }
}
