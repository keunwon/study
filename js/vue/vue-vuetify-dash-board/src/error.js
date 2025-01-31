import Vue from 'vue';

Vue.config.errorHandler = e => {
  console.log('error');
  console.log(e);
  return;
};
