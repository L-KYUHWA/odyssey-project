<template>
  <div class="container">
    <keyword-search
      @location-search="onSearchByLocationKeyword">
    </keyword-search>

    <location-list
            :result="searchResult"
            @show-detail-modal="showDetailModal"
    ></location-list>

    <location-detail-modal
      ref="detailModal">
    </location-detail-modal>

    <b-pagination
            :per-page="pageSize"
            :total-rows="totalItemCount"
            @change="onSearchByLocationKeyword"
            v-model="currentPage"
    ></b-pagination>

  </div>
</template>

<script>
  import KeywordSearch from './KeywordSearch'
  import LocationList from "./LocationList";
  import LocationDetailModal from "./LocationDetailModal"

  export default {
    name: "MainContent",
    data() {
      return {
        searchResult: null,
        totalItemCount: 0,
        pageSize: 15,
        currentPage: 1,
      }
    },
    components: {
      LocationList,
      KeywordSearch,
      LocationDetailModal
    },
    methods: {
      onSearchByLocationKeyword(keyword) {
        fetch("/api/search?keyword=" + keyword + "&pageNumber=" + this.currentPage + "&pageSize=" + this.pageSize, {
          method: 'GET'
        }).then((response) => {
          return response.json();
        }).then((data) => {
          if ('FAIL' === data.status) {
            alert("Fail to get search result");
          }

          const stringifyResult = JSON.stringify(data.result);
          const parsedResult = JSON.parse(stringifyResult);
          this.searchResult = parsedResult.documents;
          // this.totalItemCount = parsedResult.meta.total_count;

          console.log("1-----------" + this.searchResult);
          console.log("2-----------" + this.totalItemCount);

        });
      },
      showDetailModal(selectedItem) {
        this.$refs.detailModal.showModal(selectedItem);
      }
    }
  }
</script>

<style scoped>

</style>
