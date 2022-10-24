package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record=new SpecGroup();
        record.setCid(cid);
        return this.groupMapper.select(record);
    }


    public List<SpecParam> queryParams(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam record=new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setSearching(searching);
        record.setGeneric(generic);
        return this.specParamMapper.select(record);
    }

    public void saveSpecGroup(SpecGroup group) {
        groupMapper.insert(group);
    }

    public void updateSpecGroup(SpecGroup group) {
        groupMapper.updateByPrimaryKey(group);
    }

    public void deleteSpecGroup(Long id) {
        groupMapper.deleteByPrimaryKey(id);
    }

    /**
     * 在service中，我们调用之前编写过的方法，查询规格组，和规格参数，然后封装返回
     * @param cid
     * @return
     */
    public List<SpecGroup> querySpecsByCid(Long cid) {
        // 查询规格组
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        SpecParam param = new SpecParam();
        groups.forEach(g -> {
            // 查询组内参数
            g.setParams(this.queryParams(g.getId(), null, null, null));
        });
        return groups;
    }
}
