import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    editable: true,
    fireUSer: null,
    user: null,
  },
  mutations: {
    setEdit(state, editable) {
      state.editable = editable;
    },
    setFireUser(state, fireUser) {
      state.fireUSer = fireUser;
    },
    setUser(state, user) {
      state.user = user;
    },
  },
  actions: {},
  modules: {},
});
