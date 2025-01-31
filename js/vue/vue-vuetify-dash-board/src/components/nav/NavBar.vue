<template>
  <v-navigation-drawer app drawer width="400">
    <NavBarList :items="items"></NavBarList>
  </v-navigation-drawer>
</template>

<script>
import NavBarList from '@/components/nav/NavBarList.vue';

export default {
  props: ['items'],
  components: {
    NavBarList,
  },
  data() {
    return {
      drawer: true,
      right: null,
    };
  },
};
</script>

<style></style>
