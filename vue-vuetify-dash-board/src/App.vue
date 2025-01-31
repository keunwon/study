<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <Title :title="site.title"></Title>
      <v-spacer></v-spacer>
      <Sign></Sign>
      <v-btn icon @click="save">
        <v-icon>mdi-check</v-icon>
      </v-btn>
    </v-app-bar>
    <NavBar :items="site.menu"></NavBar>

    <v-content>
      <router-view></router-view>
    </v-content>

    <Footer :footer="site.footer"></Footer>
  </v-app>
</template>

<script>
import Title from '@/components/common/Title.vue';
import NavBar from '@/components/nav/NavBar.vue';
import Footer from '@/components/common/Footer.vue';
import Sign from '@/components/auth/Sign.vue';

export default {
  components: {
    Title,
    NavBar,
    Footer,
    Sign,
  },
  data() {
    return {
      site: {
        menu: [
          {
            title: 'home',
            icon: 'mdi-home',
            subItems: [
              { title: 'DashBoard', to: '/' },
              { title: 'Aboute', to: '/aboute' },
            ],
          },
          {
            title: 'aboute',
            active: true,
            icon: 'mdi-account-badge',
            subItems: [{ title: 'xxx', to: '/xxx' }],
          },
        ],
        title: '나의 타이틀입니다.',
        footer: '푸터입니다.',
      },
    };
  },
  methods: {
    subscribe() {
      this.$firebase
        .database()
        .ref()
        .child('site')
        .on(
          'value',
          sn => {
            const value = sn.val();
            if (!value) {
              this.$firebase
                .database()
                .ref()
                .child('site')
                .set(this.site);
              return;
            }
            this.site = value;
          },
          e => {
            console.log(e);
          },
        );
    },
    save() {
      this.$firebase
        .database()
        .ref()
        .child('abcd')
        .set({
          title: 'abcd',
          text: 'ttttt',
        });
    },
  },
  created() {
    this.subscribe();
  },
};
</script>

<style></style>
