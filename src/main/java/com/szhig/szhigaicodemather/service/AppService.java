package com.szhig.szhigaicodemather.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.szhig.szhigaicodemather.model.dto.app.AppAddRequest;
import com.szhig.szhigaicodemather.model.dto.app.AppQueryRequest;
import com.szhig.szhigaicodemather.model.entity.App;
import com.szhig.szhigaicodemather.model.entity.User;
import com.szhig.szhigaicodemather.model.vo.AppVO;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.util.List;

/**
 *  服务层。
 *
 * @author <a href="https://github.com/szhig"></a>
 */
public interface AppService extends IService<App> {

    AppVO getAppVO(App app);

    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    List<AppVO> getAppVOList(List<App> appList);

    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    boolean removeById(Serializable id);

    Long createApp(AppAddRequest addRequest, User loginUser);
}
