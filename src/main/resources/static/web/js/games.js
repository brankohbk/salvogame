var app = new Vue({
  el: "#app",
  data: {
    gamesList: [],
    leaderboard: [],

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



function fetchMyData(url, headers, dataHolder) {
  let promise = fetch(url, headers);
  promise
    .then(response => response.json())
    .then(myJson => {
      app[dataHolder] = myJson;
    })
    .catch(err => { `Couldn't retrieve info. please, check this error: ${err}` });
}

fetchMyData("/api/games", promiseModifiers, "gamesList");
fetchMyData("/api/leaderboard", promiseModifiers, "leaderboard");