<template>
  <b-list-group>
    <b-list-group-item variant="primary">Keyword Ranking</b-list-group-item>
    <b-list-group-item
      class="d-flex justify-content-between align-items-center"
      v-for="rank in rankKeywords">
      {{rank.keyword}}
      <b-badge pill variant="primary">{{rank.count}}</b-badge>
    </b-list-group-item>
  </b-list-group>
</template>

<script>
  export default {
    name: "KeywordRank",
    data() {
      return {
        rankKeywords: []
      }
    },
    methods: {
      updateList() {
        const requestUrl = "/api/keywords/rank";
        this.$axios.get(`${requestUrl}`)
          .then((result) => {

            if (result.data.statusCode !== 'S001') {
              console.log("keyword ranking request error : " + result);
              return;
            }

            this.rankKeywords = result.data.response;
          });
      }
    },
    mounted() {
      this.updateList();
    }
  }
</script>

<style scoped>

</style>
