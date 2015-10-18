/*******************************************************************************
 * Copyright (c) 2013-2015 LAAS-CNRS (www.laas.fr)
 * 7 Colonel Roche 31077 Toulouse - France
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thierry Monteil (Project co-founder) - Management and initial specification,
 *         conception and documentation.
 *     Mahdi Ben Alaya (Project co-founder) - Management and initial specification,
 *         conception, implementation, test and documentation.
 *     Christophe Chassot - Management and initial specification.
 *     Khalil Drira - Management and initial specification.
 *     Guillaume Garzone - Conception, implementation, test and documentation.
 *     Francois Aissaoui - Conception, implementation, test and documentation.
 ******************************************************************************/

package org.eclipse.om2m.commons.resource;
/**
 * Class referencing all persisted entities.
 * @author <ul>
 *         <li>Francois Aissaoui < aissaoui@laas.fr > </li>
 *         <li>Guillaume Garzone < garzone@laas.fr > </li>       
 *         </ul>
 */
public final class DBEntities {

	private DBEntities(){};
	
	// List of all Entities persisted in the database
	/** Name used for the persisted {@link AccessRight} entity */
	public static final String ACCESSRIGHT_ENTITY = "ACCESSRIGHT";
	/** Name used for the persisted {@link AccessRightAnnc} entity */
	public static final String ACCESSRIGHT_ANNC_ENTITY = "ACCESSRIGHTANNC";
	/** Name used for the persisted {@link APoCPaths} entity */
	public static final String APOCPATHS_ENTITY = "APOCPATHS";
	/** Name used for the persisted {@link Application} entity */
	public static final String APPLICATION_ENTITY = "APPLICATION";
	/** Name used for the persisted {@link ApplicationAnnc} entity */
	public static final String APPLICATION_ANNC_ENTITY = "APPLICATIONANNC";
	/** Name used for the persisted {@link AttachedDevice} entity */
	public static final String ATTACHED_DEVICE_ENTITY = "ATTACHEDDEVICE";
	/** Name used for the persisted {@link ChannelData} entity */
	public static final String CHANNEL_DATA_ENTITY = "CHANNELDATA";
	/** Name used for the persisted {@link Container} entity */
	public static final String CONTAINER_ENTITY = "CONTAINER";
	/** Name used for the persisted {@link ContainerAnnc} entity */
	public static final String CONTAINER_ANNC_ENTITY = "CONTAINERANNC";
	/** Name used for the persisted {@link ContentInstance} entity */
	public static final String CONTENT_INSTANCE_ENTITY = "CONTENTINSTANCE";
	/** Name used for the persisted {@link ContentInstances} entity */
	public static final String CONTENT_INSTANCES_ENTITY = "CONTENTINSTANCES";
	/** Name used for the persisted {@link ExecInstance} entity */
	public static final String EXEC_INSTANCE_ENTITY = "EXECINSTANCE";
	/** Name used for the persisted {@link Group} entity */
	public static final String GROUP_ENTITY = "GROUPENTITY"; // do not use GROUP, JPQL/SQL forbidden term
	/** Name used for the persisted {@link GroupAnnc} entity */
	public static final String GROUP_ANNC_ENTITY = "GROUPANNC";
	/** Name used for the persisted {@link LocationContainer} entity */
	public static final String LOCATION_CONTAINER_ENTITY = "LOCATIONCONTAINER";
	/** Name used for the persisted {@link LocationContainerAnnc} entity */
	public static final String LOCATION_CONTAINER_ANNC_ENTITY = "LOCATIONCONTAINERANNC";
	/** Name used for the persisted {@link M2MPoc} entity */
	public static final String M2MPOC_ENTITY = "M2MPOC";
	/** Name used for the persisted {@link MgmtCmd} entity */
	public static final String MGMTCMD_ENTITY = "MGMTCMD";
	/** Name used for the persisted {@link MgmtObj} entity */
	public static final String MGMTOBJ_ENTITY = "MGMTOBJ";
	/** Name used for the persisted {@link NotificationChannel} entity */
	public static final String NOTIFICATION_CHANNEL_ENTITY = "NOTIFICATIONCHANNEL";
	/** Name used for the persisted {@link Parameters} entity */
	public static final String PARAMETERS_ENTITY = "PARAMETERS";
	/** Name used for the persisted {@link PermissionListType} entity */
	public static final String PERMISSION_LIST_TYPE_ENTITY = "PERMISSIONLISTTYPE";
	/** Name used for the persisted {@link Scl} entity */
	public static final String SCL_ENTITY = "SCL";
	/** Name used for the persisted {@link SclBase} entity */
	public static final String SCL_BASE_ENTITY = "SCLBASE";
	/** Name used for the persisted {@link Subscription} entity */
	public static final String SUBSCRIPTION_ENTITY = "SUBSCRIPTION";	

	/** List of all entities that contains an URI (for Discover operation) */
	public static final String[] ENTITY_LIST = {
		ACCESSRIGHT_ENTITY,
		ACCESSRIGHT_ANNC_ENTITY,
		APPLICATION_ENTITY,
		APPLICATION_ANNC_ENTITY,
		ATTACHED_DEVICE_ENTITY,
		CONTAINER_ENTITY,
		CONTAINER_ANNC_ENTITY,
		CONTENT_INSTANCE_ENTITY,
		CONTENT_INSTANCES_ENTITY,
		EXEC_INSTANCE_ENTITY,
		GROUP_ENTITY,
		GROUP_ANNC_ENTITY,
		LOCATION_CONTAINER_ENTITY,
		LOCATION_CONTAINER_ANNC_ENTITY,
		M2MPOC_ENTITY,
		MGMTOBJ_ENTITY,
		MGMTCMD_ENTITY,
		NOTIFICATION_CHANNEL_ENTITY,
		PARAMETERS_ENTITY,
		SCL_ENTITY,
		SCL_BASE_ENTITY,
		SUBSCRIPTION_ENTITY
	} ;
}
