<template>
  <div>
      
    <form @submit.prevent="postPortfolio">

      <!-- title -->
      <v-layout class="write-title-input">
        <v-flex xs12>
          <v-text-field
          v-model="title"
          dark
          color="#FAFAFA"
          outline single-line
          height="50" style="font-size: 30px" name="title" required></v-text-field>
        </v-flex>
      </v-layout>


      <!-- text view area -->
      <v-layout justify-center>
        <div>
          <v-img class="portfolio-image" id="image" v-bind:src="imageSource" style="max-width: 100%"></v-img>
          
          <div class="portfolio-image">
            <imageUploader @passUploadImage="setImageSource" />
          </div>
        </div>
      </v-layout>

      <!-- text area -->
      <v-textarea
        color="#FAFAFA" class="portfolio-text" id="text-line"
        rows="10"
        outline dark auto-grow flat
        v-model="content" name="content" required>
      </v-textarea>


      <!-- submit button -->
      <v-layout justify-end>
        <v-btn
          :class="{'red-color': this.writePicked}" color="#FAFAFA"
          flat outline to="/portfolios">
          <div @mouseover="writePick" @mouseleave="writePick">
            LIST
          </div>
        </v-btn>
        <v-btn
          :class="{'red-color': this.listPicked}" color="#FAFAFA"
          flat outline>
          <input type="submit" value="WRITE" @mouseover="listPick" @mouseleave="listPick">
        </v-btn>
      </v-layout>

    </form>

  </div>
</template>


<script>
/**
 * 사용자로 부터 입력을 받아 portfolios를 작성하는 component
 */

import imageUploader from '../../components/image/ImageUploader'
import PortfolioService from '../../service/PortfolioService'
import AdminService from '../../service/AdminService'


export default {
  name: 'PortfolioWrite',
  
  components: {
    imageUploader,
  },

  data() {
    return {
      title: "",
      content: "",
      imageSource: "http://dy.gnch.or.kr/img/no-image.jpg",
      listPicked: false,
      writePicked: false,
    }
  },

  methods: {
    // POST portfolio
    async postPortfolio() {
      
      const jsonData = {
        title: this.title,
        content: this.content,
        count: 0,
        date: Date.now().toString(),
        img: this.imageSource,
        mid: this.$store.state.mid,
      };
      
      let response = [];
      response = await PortfolioService.postPortfolio(jsonData)
    },

    // set portfolio Image
    setImageSource(resultLink) {
      this.imageSource = resultLink
    },

    listPick() { this.listPicked = !this.listPicked },
    writePick() { this.writePicked = !this.writePicked },
  },
  
  async created() {
    let grade = await AdminService.getGrade(sessionStorage.getItem("jwt"));
    
    if(grade !== '0' && grade !== '1'){
      alert("권한이 없습니다.")
      window.location.href="/portfolios"
    }
  },

}
</script>


<style>
#text-line {
  line-height: 3rem !important
}
</style>