<template>

  <b-modal
    ref="modal"
    size="lg"
    title="BootstrapVue"
    v-model="show">
    <div id="map"></div>

  </b-modal>
</template>

<script>

  export default {
    name: "LocationDetailModal",
    data() {
      return {
        show: false,
        selectedItem: null,
        appKey: 'a980d54adf8bad772487124ad061074b'
      }
    },
    created() {
    },
    methods: {
      showModal(selectedItem) {
        this.selectedItem = selectedItem;
        this.$refs.modal.show();

        var container = document.getElementById('map');
        var options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 3
        };

        var map = new kakao.maps.Map(container, options);
      },
    }
  }

</script>

<style scoped>
  .map {
    width: 500px;
    height: 400px;
  }

</style>
