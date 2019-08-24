var app = new Vue({
  el: "#gamesList",
  data: {
    games: [],

  },
  methods: {

  }
});

const gamesURL = "/api/games";

var promiseModifiers = {
  method: 'GET',
  mode: 'no-cors',
  cache: 'default'
};

let dataPromise = fetch(gamesURL, promiseModifiers);
dataPromise
  .then(response => response.json())
  .then(myJson => {
    app.games = myJson;
  })
  .catch(err => { `Couldn't retrieve info. please, check this error: ${err}` });