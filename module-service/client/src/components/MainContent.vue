<template>
  <div class="container">
    <keyword-search
      @location-search="onSearchByLocationKeyword">
    </keyword-search>

    <b-table
      :fields="fields"
      :items="searchResult"
      :style="searchResultVisibility"
      @row-clicked="showDetailModal"
      hover>
    </b-table>

    <location-detail-modal
      ref="detailModal">
    </location-detail-modal>

    <b-pagination
      :per-page="pageSize"
      :style="searchResultVisibility"
      :total-rows="totalItemCount"
      @change="onPageChange"
      v-model="currentPage"
    ></b-pagination>

    <b-row>
      <b-col>
        <keyword-rank
          ref="keywordRanking">
        </keyword-rank>
      </b-col>
      <b-col>
        <my-keyword-history
          ref="keywordHistory">
        </my-keyword-history>
      </b-col>
    </b-row>
  </div>
</template>

<script>
  import KeywordSearch from './KeywordSearch'
  import LocationDetailModal from "./LocationDetailModal"
  import KeywordRank from "./KeywordRank"
  import MyKeywordHistory from "./MyKeywordHistory"

  export default {
    name: "MainContent",
    computed: {
      searchResultVisibility() {
        if (this.searchResult) {
          return '';
        }

        return 'display:none'
      }
    },
    data() {
      return {
        searchResult: null,
        totalItemCount: 0,
        pageSize: 10,
        currentPage: 1,
        keyword: '',
        fields: [
          {
            key: 'id',
            label: 'ID',
            sortable: true
          },
          {
            key: 'place_name',
            label: '지역명',
            sortable: true
          },
          {
            key: 'address_name',
            label: '구 주소',
            sortable: true
          },
          {
            key: 'road_address_name',
            label: '신 주소',
            sortable: false
          },
          {
            key: 'phone',
            label: '전환번호',
            sortable: false
          }
        ],
      }
    },
    components: {
      KeywordSearch,
      LocationDetailModal,
      KeywordRank,
      MyKeywordHistory
    },
    methods: {
      onPageChange(pageNumber) {
        this.currentPage = pageNumber;
        this.onSearchByLocationKeyword(this.keyword);
      },
      onSearchByLocationKeyword(keyword) {
        if (!keyword) {
          alert('Please Input Search Keyword');
          return;
        }

        this.keyword = keyword;
        const searchUrl = "/api/search?keyword=" + this.keyword + "&pageNumber=" + this.currentPage + "&pageSize=" + this.pageSize
        this.$axios.get(`${searchUrl}`)
          .then((result) => {

            if (result.data.statusCode !== 'S001') {
              console.log("search request error : " + result);
              return;
            }

            const parseResult = JSON.parse(result.data.response);
            this.searchResult = parseResult.documents;
            this.totalItemCount = parseResult.meta.total_count;
          })
          .then(() => {
            this.$refs.keywordRanking.updateList();
            this.$refs.keywordHistory.updateList();
        });

      },
      showDetailModal(selectedItem) {
        this.$refs.detailModal.showModal(selectedItem);
      },
      onRowClick(selectedItem) {
        this.$emit('show-detail-modal', selectedItem);
      }
    }
  }
</script>

<style scoped>

</style>
