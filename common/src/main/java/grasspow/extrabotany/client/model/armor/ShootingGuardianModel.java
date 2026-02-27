package grasspow.extrabotany.client.model.armor;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ShootingGuardianModel {
    public static MeshDefinition createInsideMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition bone56 = right_leg.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(36, 48).addBox(-0.5F, -2.5F, -1.8F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, 1.5F, -0.5F, -0.7854F, 0.0F, 0.0873F));

        PartDefinition bone57 = right_leg.addOrReplaceChild("bone57", CubeListBuilder.create().texOffs(20, 9).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition bone60 = right_leg.addOrReplaceChild("bone60", CubeListBuilder.create().texOffs(36, 43).addBox(-3.5F, 0.8F, -0.1F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 4.5F, -2.4F, 0.1745F, -0.1745F, 0.6981F));

        PartDefinition bone61 = right_leg.addOrReplaceChild("bone61", CubeListBuilder.create().texOffs(0, 0).addBox(0.1F, -2.7F, -0.1F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 4.5F, -2.4F, 0.1745F, -0.1745F, 0.8727F));

        PartDefinition bone58 = right_leg.addOrReplaceChild("bone58", CubeListBuilder.create().texOffs(15, 0).addBox(-2.0F, -2.0F, -0.1F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 2.0F, -2.5F, 0.1745F, -0.1745F, 0.7854F));

        PartDefinition bone59 = right_leg.addOrReplaceChild("bone59", CubeListBuilder.create().texOffs(30, 15).addBox(0.0F, 0.0F, -0.4F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 2.0F, -2.5F, 0.0873F, -0.0873F, 0.7854F));

        PartDefinition bone62 = right_leg.addOrReplaceChild("bone62", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -1.5F, -0.14F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 6.5F, -2.35F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone63 = right_leg.addOrReplaceChild("bone63", CubeListBuilder.create().texOffs(14, 31).addBox(-4.5F, -10.0F, -2.3F, 5.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition bone66 = right_leg.addOrReplaceChild("bone66", CubeListBuilder.create().texOffs(48, 21).addBox(-4.5F, -7.0F, 0.7F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition bone64 = right_leg.addOrReplaceChild("bone64", CubeListBuilder.create().texOffs(0, 30).addBox(-4.3F, -11.4F, -1.7F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition bone65 = right_leg.addOrReplaceChild("bone65", CubeListBuilder.create().texOffs(0, 16).addBox(-2.7F, -11.41F, -1.71F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition bone105 = left_leg.addOrReplaceChild("bone105", CubeListBuilder.create().texOffs(36, 48).mirror().addBox(-0.5F, -2.5F, -1.8F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.6F, 1.5F, -0.5F, -0.7854F, 0.0F, -0.0873F));

        PartDefinition bone106 = left_leg.addOrReplaceChild("bone106", CubeListBuilder.create().texOffs(20, 9).mirror().addBox(-2.5F, -0.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 1.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition bone107 = left_leg.addOrReplaceChild("bone107", CubeListBuilder.create().texOffs(36, 43).mirror().addBox(-0.5F, 0.8F, -0.1F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.4F, 4.5F, -2.4F, 0.1745F, 0.1745F, -0.6981F));

        PartDefinition bone108 = left_leg.addOrReplaceChild("bone108", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.1F, -2.7F, -0.1F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.4F, 4.5F, -2.4F, 0.1745F, 0.1745F, -0.8727F));

        PartDefinition bone109 = left_leg.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(-2.0F, -2.0F, -0.1F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 2.0F, -2.5F, 0.1745F, 0.1745F, -0.7854F));

        PartDefinition bone110 = left_leg.addOrReplaceChild("bone110", CubeListBuilder.create().texOffs(30, 15).mirror().addBox(-3.0F, 0.0F, -0.4F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 2.0F, -2.5F, 0.0873F, 0.0873F, -0.7854F));

        PartDefinition bone111 = left_leg.addOrReplaceChild("bone111", CubeListBuilder.create().texOffs(0, 30).mirror().addBox(-1.0F, -1.5F, -0.14F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 6.5F, -2.35F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone112 = left_leg.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(14, 31).mirror().addBox(-0.5F, -10.0F, -2.3F, 5.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition bone113 = left_leg.addOrReplaceChild("bone113", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-0.5F, -7.0F, 0.7F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition bone114 = left_leg.addOrReplaceChild("bone114", CubeListBuilder.create().texOffs(0, 30).mirror().addBox(1.3F, -11.4F, -1.7F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition bone115 = left_leg.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.3F, -11.41F, -1.71F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

        root.addOrReplaceChild("bootL", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("bootR", CubeListBuilder.create(), PartPose.ZERO);
        return mesh;
    }

    public static MeshDefinition createOutsideMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();


        PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition bone54 = right_arm.addOrReplaceChild("bone54", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

        PartDefinition bone27 = right_arm.addOrReplaceChild("bone27", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(48, 43).addBox(-8.4F, -14.5F, -2.35F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone30 = bone28.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(35, 27).addBox(-8.4F, -14.5F, -0.65F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone32 = bone27.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -12.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(10, 16).addBox(-8.4F, -13.4F, -2.35F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 12.0F, 0.0F));

        PartDefinition bone34 = bone32.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(49, 14).addBox(-8.4F, -13.4F, -0.65F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 12.0F, 0.0F));

        PartDefinition bone24 = right_arm.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

        PartDefinition bone25 = bone24.addOrReplaceChild("bone25", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone17 = bone25.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(35, 21).addBox(-8.9F, -19.0F, -0.4F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone16 = bone25.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(30, 37).addBox(-8.9F, -19.0F, -2.6F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone26 = bone24.addOrReplaceChild("bone26", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone15 = bone26.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(23, 50).addBox(-8.81F, -22.0F, -2.35F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone14 = bone26.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(25, 43).addBox(-8.8F, -22.0F, -0.65F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone20 = right_arm.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 8.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone31 = bone20.addOrReplaceChild("bone31", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition bone18 = bone31.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(49, 0).addBox(-1.9132F, -1.4756F, -1.6274F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 1.3F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone19 = bone31.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(48, 38).addBox(-1.6132F, -1.4756F, -1.3726F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.81F, -1.0F, -1.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone21 = bone31.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(8, 56).addBox(-0.2F, -1.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -2.5F, -0.5F, -0.7854F, 0.0F, 0.0F));

        PartDefinition bone7 = right_arm.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -2.0F, 0.5F, 0.0F, 0.0F, 1.7453F));

        PartDefinition bone11 = bone7.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(38, 0).addBox(-6.0F, -23.0F, -2.51F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 24.0F, -0.5F));

        PartDefinition bone12 = bone7.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(22, 56).addBox(-1.5F, -1.0F, 0.2F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.0F, -3.1F));

        PartDefinition bone13 = bone7.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(52, 32).addBox(-6.0F, -26.0F, 1.4F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 24.0F, -0.5F));

        PartDefinition bone6 = right_arm.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -2.0F, 0.5F, 0.0F, 0.0F, 1.8326F));

        PartDefinition bone8 = bone6.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(43, 32).addBox(-6.0F, -23.0F, -2.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 24.0F, -0.5F));

        PartDefinition bone9 = bone6.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(53, 9).addBox(1.0F, -3.0F, -3.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone10 = bone6.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(4, 53).addBox(-6.5F, -27.0F, 1.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 24.0F, -0.5F));

        PartDefinition bone = right_arm.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -2.0F, 0.5F, 0.0F, 0.0F, 1.1345F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(41, 41).addBox(-8.0F, -26.0F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 24.0F, -0.5F));

        PartDefinition bone5 = bone.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(49, 49).addBox(-7.0F, -22.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 24.0F, -0.5F));

        PartDefinition bone3 = bone.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(35, 54).addBox(0.0F, -1.5F, -3.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone4 = bone.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(48, 54).addBox(-7.5F, -25.5F, 1.6F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 24.0F, -0.5F));

        PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition bone55 = left_arm.addOrReplaceChild("bone55", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

        PartDefinition bone73 = left_arm.addOrReplaceChild("bone73", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

        PartDefinition bone74 = bone73.addOrReplaceChild("bone74", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone75 = bone74.addOrReplaceChild("bone75", CubeListBuilder.create().texOffs(48, 43).mirror().addBox(3.4F, -14.5F, -2.35F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone76 = bone74.addOrReplaceChild("bone76", CubeListBuilder.create().texOffs(35, 27).mirror().addBox(3.4F, -14.5F, -0.65F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone77 = bone73.addOrReplaceChild("bone77", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -12.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone78 = bone77.addOrReplaceChild("bone78", CubeListBuilder.create().texOffs(10, 16).mirror().addBox(5.4F, -13.4F, -2.35F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 12.0F, 0.0F));

        PartDefinition bone79 = bone77.addOrReplaceChild("bone79", CubeListBuilder.create().texOffs(49, 14).mirror().addBox(5.4F, -13.4F, -0.65F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 12.0F, 0.0F));

        PartDefinition bone80 = left_arm.addOrReplaceChild("bone80", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

        PartDefinition bone81 = bone80.addOrReplaceChild("bone81", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create().texOffs(35, 21).mirror().addBox(3.9F, -19.0F, -0.4F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone83 = bone81.addOrReplaceChild("bone83", CubeListBuilder.create().texOffs(30, 37).mirror().addBox(3.9F, -19.0F, -2.6F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone84 = bone80.addOrReplaceChild("bone84", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone85 = bone84.addOrReplaceChild("bone85", CubeListBuilder.create().texOffs(23, 50).mirror().addBox(4.81F, -22.0F, -2.35F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone86 = bone84.addOrReplaceChild("bone86", CubeListBuilder.create().texOffs(25, 43).mirror().addBox(4.8F, -22.0F, -0.65F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone87 = left_arm.addOrReplaceChild("bone87", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition bone89 = bone88.addOrReplaceChild("bone89", CubeListBuilder.create().texOffs(49, 0).mirror().addBox(-2.0868F, -1.4756F, -1.6274F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, -1.0F, 1.3F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone90 = bone88.addOrReplaceChild("bone90", CubeListBuilder.create().texOffs(48, 38).mirror().addBox(-2.3868F, -1.4756F, -1.3726F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.81F, -1.0F, -1.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone91 = bone88.addOrReplaceChild("bone91", CubeListBuilder.create().texOffs(8, 56).mirror().addBox(-0.8F, -1.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, -2.5F, -0.5F, -0.7854F, 0.0F, 0.0F));

        PartDefinition bone92 = left_arm.addOrReplaceChild("bone92", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, -2.0F, 0.5F, 0.0F, 0.0F, -1.7453F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create().texOffs(38, 0).mirror().addBox(3.0F, -23.0F, -2.51F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 24.0F, -0.5F));

        PartDefinition bone94 = bone92.addOrReplaceChild("bone94", CubeListBuilder.create().texOffs(22, 56).mirror().addBox(-0.5F, -1.0F, 0.2F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -1.0F, -3.1F));

        PartDefinition bone95 = bone92.addOrReplaceChild("bone95", CubeListBuilder.create().texOffs(52, 32).mirror().addBox(3.0F, -26.0F, 1.4F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 24.0F, -0.5F));

        PartDefinition bone96 = left_arm.addOrReplaceChild("bone96", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, -2.0F, 0.5F, 0.0F, 0.0F, -1.8326F));

        PartDefinition bone97 = bone96.addOrReplaceChild("bone97", CubeListBuilder.create().texOffs(43, 32).mirror().addBox(4.0F, -23.0F, -2.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 24.0F, -0.5F));

        PartDefinition bone98 = bone96.addOrReplaceChild("bone98", CubeListBuilder.create().texOffs(53, 9).mirror().addBox(-3.0F, -3.0F, -3.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone99 = bone96.addOrReplaceChild("bone99", CubeListBuilder.create().texOffs(4, 53).mirror().addBox(4.5F, -27.0F, 1.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 24.0F, -0.5F));

        PartDefinition bone100 = left_arm.addOrReplaceChild("bone100", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, -2.0F, 0.5F, 0.0F, 0.0F, -1.1345F));

        PartDefinition bone101 = bone100.addOrReplaceChild("bone101", CubeListBuilder.create().texOffs(41, 41).mirror().addBox(7.0F, -26.0F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 24.0F, -0.5F));

        PartDefinition bone102 = bone100.addOrReplaceChild("bone102", CubeListBuilder.create().texOffs(49, 49).mirror().addBox(5.0F, -22.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 24.0F, -0.5F));

        PartDefinition bone103 = bone100.addOrReplaceChild("bone103", CubeListBuilder.create().texOffs(35, 54).mirror().addBox(-2.0F, -1.5F, -3.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone104 = bone100.addOrReplaceChild("bone104", CubeListBuilder.create().texOffs(48, 54).mirror().addBox(5.5F, -25.5F, 1.6F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 24.0F, -0.5F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone36 = body.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(35, 12).addBox(-1.5F, -0.5F, -0.6F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition bone48 = body.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(35, 9).addBox(-1.5F, -0.5F, 3.6F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition bone37 = body.addOrReplaceChild("bone37", CubeListBuilder.create().texOffs(53, 19).addBox(-1.5F, 0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition bone38 = body.addOrReplaceChild("bone38", CubeListBuilder.create().texOffs(43, 38).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 2.0F, -2.0F));

        PartDefinition bone39 = body.addOrReplaceChild("bone39", CubeListBuilder.create().texOffs(28, 56).addBox(-1.5F, -1.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone40 = body.addOrReplaceChild("bone40", CubeListBuilder.create().texOffs(0, 44).addBox(-2.0F, -2.0F, -0.41F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone41 = body.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(13, 43).addBox(0.0F, 0.0F, -0.4F, 5.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition bone47 = body.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(15, 15).addBox(-2.0F, 0.0F, -0.38F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition bone49 = body.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -0.01F, -0.37F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition bone46 = body.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(54, 54).addBox(1.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(51, 24).addBox(-2.0F, 1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, -1.89F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone42 = body.addOrReplaceChild("bone42", CubeListBuilder.create().texOffs(20, 9).addBox(-1.0F, 1.0F, -0.41F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition bone43 = body.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(0, 16).addBox(5.0F, 1.0F, -0.41F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition bone44 = body.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(0, 53).addBox(-1.5F, -4.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 6.0F, -1.92F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone45 = body.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(18, 52).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 6.0F, -1.92F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone50 = body.addOrReplaceChild("bone50", CubeListBuilder.create().texOffs(44, 6).addBox(-1.0F, 9.1F, -0.4F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.0F, -2.0F));

        PartDefinition medal = body.addOrReplaceChild("medal", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightMedal = medal.addOrReplaceChild("rightMedal", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone35 = rightMedal.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(53, 46).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -21.0F, -2.5F, 0.0F, 0.0F, 0.6981F));

        PartDefinition bone51 = rightMedal.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(14, 52).addBox(-1.0F, -5.0F, -0.3F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -16.0F, -2.8F, -0.0873F, 0.0F, 0.0F));

        PartDefinition leftMedal = medal.addOrReplaceChild("leftMedal", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone52 = leftMedal.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(27, 31).addBox(4.0F, -7.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -21.0F, -2.5F, 0.0F, 0.0F, 0.8727F));

        PartDefinition bone53 = leftMedal.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(44, 51).addBox(-1.1F, -5.0F, -0.3F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -16.0F, -2.8F, -0.0873F, 0.0F, 0.0F));

        PartDefinition bootR = root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition bone67 = bootR.addOrReplaceChild("bone67", CubeListBuilder.create().texOffs(20, 0).addBox(-5.0F, -2.2F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition bone70 = bootR.addOrReplaceChild("bone70", CubeListBuilder.create().texOffs(35, 15).addBox(-5.3F, -1.2F, -3.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9F, 12.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition bone71 = bootR.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(30, 31).addBox(-3.7F, -1.19F, -3.2F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9F, 12.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition bone68 = bootR.addOrReplaceChild("bone68", CubeListBuilder.create().texOffs(0, 48).addBox(0.01F, -0.5F, -3.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.5F, 2.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone69 = bootR.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(47, 27).addBox(-3.01F, -0.5F, -3.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.5F, 2.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone72 = bootR.addOrReplaceChild("bone72", CubeListBuilder.create().texOffs(10, 30).addBox(-3.8F, 0.5F, -3.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.5F, 2.5F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bootL = root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition bone116 = bootL.addOrReplaceChild("bone116", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-1.0F, -2.2F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition bone117 = bootL.addOrReplaceChild("bone117", CubeListBuilder.create().texOffs(35, 15).mirror().addBox(0.3F, -1.2F, -3.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.9F, 12.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition bone118 = bootL.addOrReplaceChild("bone118", CubeListBuilder.create().texOffs(30, 31).mirror().addBox(-1.3F, -1.19F, -3.2F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.9F, 12.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition bone119 = bootL.addOrReplaceChild("bone119", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-3.01F, -0.5F, -3.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.5F, 2.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone120 = bootL.addOrReplaceChild("bone120", CubeListBuilder.create().texOffs(47, 27).mirror().addBox(0.01F, -0.5F, -3.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.5F, 2.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone121 = bootL.addOrReplaceChild("bone121", CubeListBuilder.create().texOffs(10, 30).mirror().addBox(2.8F, 0.5F, -3.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.5F, 2.5F, -0.3491F, 0.0F, 0.0F));

        PartDefinition hat = root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone233 = hat.addOrReplaceChild("bone233", CubeListBuilder.create().texOffs(86, 25).addBox(-3.0F, -2.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, 0.0F, 0.2618F, 0.0F));

        PartDefinition bone244 = hat.addOrReplaceChild("bone244", CubeListBuilder.create().texOffs(83, 0).addBox(0.0F, -2.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, 0.0F, -0.2618F, 0.0F));

        PartDefinition bone344 = hat.addOrReplaceChild("bone344", CubeListBuilder.create().texOffs(64, 20).addBox(-5.0F, -31.0F, -4.3F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone544 = hat.addOrReplaceChild("bone544", CubeListBuilder.create().texOffs(83, 0).addBox(5.0F, -29.0F, -4.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone444 = hat.addOrReplaceChild("bone444", CubeListBuilder.create().texOffs(86, 17).addBox(-5.0F, -29.0F, -4.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone611 = hat.addOrReplaceChild("bone611", CubeListBuilder.create().texOffs(70, 10).addBox(0.5F, -4.0F, 4.49F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-5.8F, -4.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone1322 = hat.addOrReplaceChild("bone1322", CubeListBuilder.create().texOffs(64, 0).addBox(0.5F, -4.0F, -4.48F, 3.0F, 8.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-5.8F, -4.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone1111 = hat.addOrReplaceChild("bone1111", CubeListBuilder.create().texOffs(83, 19).addBox(0.5F, -4.0F, -4.49F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8F, -4.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone1923 = hat.addOrReplaceChild("bone1923", CubeListBuilder.create().texOffs(94, 20).addBox(-0.9F, -1.1F, -2.19F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-5.8F, -4.0F, 0.0F, 0.0F, -0.7854F, 0.1745F));

        PartDefinition bone2034 = hat.addOrReplaceChild("bone2034", CubeListBuilder.create().texOffs(76, 28).addBox(-0.1F, -1.1F, -2.19F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(5.8F, -4.0F, 0.0F, 0.0F, 0.7854F, -0.1745F));

        PartDefinition bone1733 = hat.addOrReplaceChild("bone1733", CubeListBuilder.create().texOffs(64, 34).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-5.1F, -4.0F, -1.5F, -0.6981F, 0.0F, 0.1745F));

        PartDefinition bone1821 = hat.addOrReplaceChild("bone1821", CubeListBuilder.create().texOffs(96, 24).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1F, -4.0F, -1.5F, -0.6981F, 0.0F, -0.1745F));

        PartDefinition bone721 = hat.addOrReplaceChild("bone721", CubeListBuilder.create().texOffs(83, 1).addBox(-1.5F, -4.0F, -4.49F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(5.8F, -4.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone1021 = hat.addOrReplaceChild("bone1021", CubeListBuilder.create().texOffs(70, 0).addBox(-1.5F, -4.0F, 4.49F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(5.8F, -4.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone1221 = hat.addOrReplaceChild("bone1221", CubeListBuilder.create().texOffs(64, 10).addBox(-3.5F, -4.0F, -4.48F, 3.0F, 8.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(5.8F, -4.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone834 = hat.addOrReplaceChild("bone834", CubeListBuilder.create().texOffs(64, 10).addBox(-2.3F, -0.5F, -4.5F, 5.0F, 1.0F, 9.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.5F, -7.5F, 0.02F, 0.0F, 0.0F, -0.2618F));

        PartDefinition bone1555 = hat.addOrReplaceChild("bone1555", CubeListBuilder.create().texOffs(64, 0).addBox(-2.7F, -0.5F, -4.5F, 5.0F, 1.0F, 9.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.5F, -7.5F, 0.02F, 0.0F, 0.0F, 0.2618F));

        PartDefinition bone966 = hat.addOrReplaceChild("bone966", CubeListBuilder.create().texOffs(94, 0).addBox(-10.8F, -4.0F, 3.5F, 10.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.8F, -4.0F, 0.0F));

        PartDefinition bone1477 = hat.addOrReplaceChild("bone1477", CubeListBuilder.create().texOffs(94, 18).addBox(-10.8F, -4.0F, -4.5F, 10.0F, 2.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offset(5.8F, -4.0F, 0.0F));

        PartDefinition bone1688 = hat.addOrReplaceChild("bone1688", CubeListBuilder.create().texOffs(64, 28).addBox(-1.5F, -4.5F, -0.01F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -5.0F, -4.5F, 0.0F, 0.0F, -0.7854F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        return mesh;
    }
}