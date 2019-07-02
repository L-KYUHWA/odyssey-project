<template>
  <b-list-group>
    <b-list-group-item variant="primary">My Search Keyword History</b-list-group-item>
    <b-list-group-item
      class="d-flex justify-content-between align-items-center"
      v-for="history in keywordHistory">
      {{parseDate(history.modifiedAt)}} - {{history.keyword}}
    </b-list-group-item>
  </b-list-group>
</template>

<script>
  export default {
    name: "MyKeywordHistoryVue",
    data() {
      return {
        keywordHistory: []
      }
    },
    methods: {
      updateList() {
        const requestUrl = "/api/my-keyword-history";
        this.$axios.get(`${requestUrl}`)
          .then((result) => {
            this.keywordHistory = result.data;
          });
      },
      parseDate(modifiedDate) {
        let d = new Date(modifiedDate),
          month = '' + (d.getMonth() + 1),
          day = '' + d.getDate(),
          year = d.getFullYear(),
          hour = d.getHours(),
          minute = d.getMinutes(),
          second = d.getSeconds();

        if (month.length < 2) {
          month = '0' + month;
        }

        if (day.length < 2) {
          day = '0' + day;
        }

        if (second.length < 2) {
          second = '0' + second;
        }

        return [year, month, day].join('-') + ' ' + [hour, minute, second].join(':');
      }
    },
    mounted() {
      this.updateList();
    }
  }
</script>

<style scoped>

</style>
