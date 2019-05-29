<template>
  <Row>
    <Col style="width: 150px; height: 150px; border: 1px #ccc solid; background: #ccc;">
      <Col style="width: 90%; height:90%; margin: 5%; border: 1px #fff solid; background: #fff;">
        <Col style="width: 96%; height:96%; margin: 2%; border: 1px #fff solid;">
          <img style="width: 100%; height:100%;" :src="imgUrl" />
        </Col>
      </Col>
    </Col>
  </Row>
</template>
<script>
export default {
  name: 'avatar',
  props: { imgUrl: String },
  mounted: function () {
  }
}
</script>
