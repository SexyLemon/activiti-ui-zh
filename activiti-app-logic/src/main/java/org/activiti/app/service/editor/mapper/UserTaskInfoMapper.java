/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.app.service.editor.mapper;

import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.UserTask;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserTaskInfoMapper extends AbstractInfoMapper {

	protected void mapProperties(Object element) {
		UserTask userTask = (UserTask) element;
		createPropertyNode("指派", userTask.getAssignee());
		createPropertyNode("候选用户", userTask.getCandidateUsers());
		createPropertyNode("候选组", userTask.getCandidateGroups());
		createPropertyNode("到期日期", userTask.getDueDate());
		createPropertyNode("自定义表单", userTask.getFormKey());
		createPropertyNode("优先级", userTask.getPriority());
		if (CollectionUtils.isNotEmpty(userTask.getFormProperties())) {
		    List<String> formPropertyValues = new ArrayList<String>();
		    for (FormProperty formProperty : userTask.getFormProperties()) {
		        StringBuilder propertyBuilder = new StringBuilder();
		        if (StringUtils.isNotEmpty(formProperty.getName())) {
		            propertyBuilder.append(formProperty.getName());
		        } else {
		            propertyBuilder.append(formProperty.getId());
		        }
		        if (StringUtils.isNotEmpty(formProperty.getType())) {
		            propertyBuilder.append(" - ");
		            propertyBuilder.append(formProperty.getType());
		        }
		        if (formProperty.isRequired()) {
					propertyBuilder.append(" (不为空)");
		        } else {
					propertyBuilder.append(" (可为空)");
		        }
                formPropertyValues.add(propertyBuilder.toString());
            }
			createPropertyNode("表单属性", formPropertyValues);
		}
		createListenerPropertyNodes("任务监听器", userTask.getTaskListeners());
		createListenerPropertyNodes("执行监听器", userTask.getExecutionListeners());
	}
}
