<template>
  <div>
    <v-layout justify-center>
    <radial-menu
      style="background-color: #FAFAFA"
      :itemSize="80"
      :radius="150"
      :size="80"
      :angle-restriction="270">
      
      <radial-menu-item 
        v-for="(item, index) in items" 
        :key="index" 
        style="background-color: white" 
        @click="goToPage(item)">
        <span>{{item}}</span>

      </radial-menu-item>
    </radial-menu>
    </v-layout>
  </div>
</template>


<script>
import { RadialMenu,  RadialMenuItem } from 'vue-radial-menu'

export default {
  name: "CircularMenu",

  components: {
    RadialMenu,
    RadialMenuItem,
  },

  methods: {
    goToPage(item) {
      if(item === "About Us") {
        this.$store.commit('setPageDirection', 'slide-right')
        this.$router.push({ name: 'aboutus' })
      }
      else if(item === "Portfolios") {
        this.$store.commit('setPageDirection', 'slide-up')
        this.$router.push({ name: 'portfolios' })
      }
      else if(item === "Posts") {
        this.$store.commit('setPageDirection', 'slide-left')
        this.$router.push({ name: 'posts' })
      }
      else if(item === "Home") {
        this.$store.commit('setPageDirection', 'slide-down')
        this.$router.push({ name: 'home' })
      }
    }
  },

  data() {
    return {
      items: ['About Us', 'Portfolios', 'Posts', 'Home'],
    }
  },
    
}
</script>

<style>

</style>
