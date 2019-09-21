var app = new Vue({
  el: "#app",
  data: {
    games: [],
    players: [],

  },
  methods: {
    formatDate: function(created) {
      let input = new Date(created)
      let formated = input.toLocaleString();
      return formated;
    }

  }
});



var promiseModifiers = {
  method: 'GET',
  mode: 'no-cors',
  cache: 'default'
};

fetchMyData("/api/games", promiseModifiers, "games");
fetchMyData("/rest/players", promiseModifiers, "players");

function fetchMyData(url, headers, dataHolder) {
  let promise = fetch(url, headers);
  promise
    .then(response => response.json())
    .then(myJson => {
      app[dataHolder] = myJson;
    })
    .catch(err => { `Couldn't retrieve info. please, check this error: ${err}` });
}