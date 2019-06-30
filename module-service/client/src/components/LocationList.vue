<template>
  <div>
    <b-table
      :fields="fields"
      :items="items"
      @row-clicked="onRowClick"
      hover>
    </b-table>
  </div>
</template>

<script>
  export default {
    name: "LocationList",
    props: ['searchResult'],
    data() {
      return {
        items: this.searchResult,
        fields: [
          {
            key: 'id',
            label: 'ID',
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
    methods: {
      onRowClick(selectedItem) {
        this.$emit('show-detail-modal', selectedItem);
      }
    }
  }
</script>

<style scoped>

</style>
