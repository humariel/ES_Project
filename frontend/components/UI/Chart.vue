<template>
    <chartjs-line
        :backgroundcolor="bgColor"
        :bordercolor="borderColor"
        :beginzero="true"
        :bind="true"
        :data="vals"
        :datalabel="label"
        :labels="labels"
    />
</template>

<script>
export default {
    props: {
        values: Array,
        label: String
    },
    computed: {
        labels() {
            return this.values.map(v => v.label)
        },
        vals() {
            return this.values.map(v => v.value)
        },
    },
    data() {
        return {
            bgColor: "#81894e",
            borderColor: "#81894e",
        };
    }
};
</script>