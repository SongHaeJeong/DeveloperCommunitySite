<template>
  <div style="color:white;">
    <!-- member table -->
    <table>
      <tr>
        <th>Email</th>
        <th>name</th>
        <th>PortfolioCount</th>
        <th>PostCount</th>
        <th>Grade</th>
        <th>회원 삭제</th>
      </tr>
      <tr v-for="p in memberListData" :key="p.name">
        <td>{{ p.email }}</td>
        <td>{{ p.name }}</td>
        <td>{{ p.pfCount }}</td>
        <td>{{ p.postCount}}</td>
        <td>
          <select v-model="p.grade">
            <option style="color: black;" value="0">관리자</option>
            <option style="color: black;" value="1">팀원</option>
            <option style="color: black;" value="2">방문자</option>
          </select>
        </td>
        <td v-if="midCheck(p.mid)"><v-btn @click="deleteMember(p.mid)">삭제</v-btn></td>
        <td v-else></td>
      </tr>
    </table>

    <!-- buttons -->
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
      </button>
    </div>
    <div class="btn-cover">
      <v-btn class="page-btn" @click="updateMemberList">수 정</v-btn>
      <v-btn class="page-btn" :to='{name:"home"}'>메인으로</v-btn>
    </div>
  </div>
</template>

<script>
import AdminService from '../../service/AdminService.js'

export default {
  name: 'MemberList',
  data () {
    return {
      pageNum: 0,
    }
  },

  props: {
    listArray: {
      type: Array,
      required: true
    },

    pageSize: {
      type: Number,
      required: false,
      default: 10
    }
  },

  methods: {
    nextPage () {
      this.pageNum += 1;
    },

    prevPage () {
      this.pageNum -= 1;
    },

    async updateMemberList(){
      await AdminService.updateMemberList(this.listArray);
    },

    midCheck(mid){
      let mmid=sessionStorage.getItem("mid");
      
      return mid !== mmid
    },

    async deleteMember(mid){
      let response = await AdminService.deleteMemberList(mid);

      if(response.data.success==='true'){
        alert("삭제 되었습니다.")
        window.location.href="/admin"
      }else{
        alert("삭제 에러")
      }

    }

  },

  computed: {
    pageCount () {
      let listLeng = this.listArray.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },

    memberListData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.listArray.slice(start, end);
    }
  }
}
</script>


<style>
table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 1.2rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>
