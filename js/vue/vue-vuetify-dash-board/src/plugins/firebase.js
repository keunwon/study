import Vue from 'vue';
import * as firebase from 'firebase/app';

import 'firebase/auth';
import 'firebase/firebase-database';
import 'firebase/firebase-storage';
import 'firebase/firebase-firestore';
import { firebaseConfig } from '../../firebaseConfig';
import store from '@/store/index';

firebase.initializeApp(firebaseConfig);

firebase.auth().onAuthStateChanged(user => {
  store.commit('setFireUser', user);
});
Vue.prototype.$firebase = firebase;
