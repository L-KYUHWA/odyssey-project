<template>
  <div>
    <b-row class="text-center">
      <b-col>
        <h3>
          <b-badge variant="light">Location Keyword</b-badge>
        </h3>
      </b-col>
      <b-col>
        <b-form-input :placeholder="placeHolder" v-model="keyword"></b-form-input>
        <div class="mt-2">Value: {{ keyword }}</div>
      </b-col>
      <b-col>
        <b-button
          v-on:click="keywordSearch"
          variant="outline-primary">
          Search
        </b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
  export default {
    name: "KeywordSearchBar",
    data() {
      return {
        placeHolder: "Keyword",
        keyword: ''
      }
    },
    methods: {
      keywordSearch() {
        this.$emit('location-search', this.keyword);
      }
    }
  }
</script>

<style scoped>

</style>
